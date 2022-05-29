package com.sportsdk.internal.ui

import androidx.compose.runtime.State
import com.nhaarman.mockito_kotlin.*
import com.sportsdk.internal.domain.Sport
import com.sportsdk.internal.interactor.SportInteractor
import com.sportsdk.internal.ui.SportViewState.TargetState.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SportViewModelTest {

    private val interactor = mock<SportInteractor>()

    private val viewModel by lazy {
        SportViewModel(
            interactor,
            viewStateFactory
        )
    }

    private val binding = mock<State<SportViewStateBinding>> {
        on { value } doReturn mock<SportViewStateBinding>()
    }

    private val viewState = mock<SportViewState> {
        on { binding } doReturn binding
    }

    private val viewStateFactory = mock<SportViewState.Factory> {
        on { create() } doAnswer {
            viewState
        }
    }

    @Before fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test fun `getSport - no error - moves to loading state, calls getSport, moves to content state`() = runTest {
        val sport = mock<Sport>().also {
            whenever(interactor.getSport()).thenReturn(it)
        }

        viewModel

        inOrder(viewState, interactor) {
            verify(viewState).binding
            verify(viewState).moveTo(Loading)
            runBlocking {
                verify(interactor).getSport()
            }
            verify(viewState).moveTo(Content(sport))
        }
    }

    @Test fun `getSport - error - moves to error state`() = runTest {
        whenever(interactor.getSport()).doAnswer { throw Exception("Error getting sport") }

        viewModel

        inOrder(viewState) {
            verify(viewState).binding
            verify(viewState).moveTo(Error)
        }
    }
}