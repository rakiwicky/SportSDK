package com.sportsdk.internal.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sportsdk.internal.interactor.SportInteractor
import com.sportsdk.internal.ui.SportViewState.TargetState.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class SportViewModel @Inject constructor(
    private val sportsInteractor: SportInteractor,
    viewStateFactory: SportViewState.Factory
) : ViewModel() {

    private val viewState = viewStateFactory.create()
    val binding = viewState.binding

    init {
        loadSport()
    }

    fun loadSport() {
        viewState.moveTo(Loading)
        viewModelScope.launch(Dispatchers.Default) {
            try {
                val sport = sportsInteractor.getSport()
                withContext(Dispatchers.Main) {
                    viewState.moveTo(Content(sport))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    viewState.moveTo(Error)
                }
            }
        }
    }
}