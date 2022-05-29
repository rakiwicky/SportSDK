package com.sportsdk.internal.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.sportsdk.internal.domain.Sport
import com.sportsdk.internal.ui.SportViewState.TargetState.*
import javax.inject.Inject

internal class SportViewState {

    private val _binding = mutableStateOf(
        SportViewStateBinding(
            sportName = "",
            sportDescription = "",
            loadingVisible = false,
            contentVisible = false,
            errorVisible = false
        )
    )

    val binding: State<SportViewStateBinding> = _binding

    fun moveTo(targetState: TargetState) {
        val currentState = _binding.value

        _binding.value = when (targetState) {
            is Content -> currentState.copy(
                sportName = targetState.Sport.name,
                sportDescription = targetState.Sport.description,
                loadingVisible = false,
                contentVisible = true,
                errorVisible = false
            )
            Loading -> currentState.copy(
                loadingVisible = true,
                contentVisible = false,
                errorVisible = false
            )
            Error -> currentState.copy(
                loadingVisible = false,
                contentVisible = false,
                errorVisible = true
            )
        }
    }

    class Factory @Inject constructor() {
        fun create(
        ) = SportViewState()
    }

    sealed class TargetState {
        data class Content(val Sport: Sport) : TargetState()
        object Loading : TargetState()
        object Error : TargetState()
    }
}