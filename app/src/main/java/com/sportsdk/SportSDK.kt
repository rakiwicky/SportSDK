package com.sportsdk

import androidx.compose.runtime.Composable
import com.sportsdk.internal.di.DaggerAppComponent
import com.sportsdk.internal.ui.SportView
import javax.inject.Inject

class SportSDK private constructor() {

    @Inject
    internal lateinit var sportView: SportView

    init {
        DaggerAppComponent.builder().build().inject(this)
    }

    @Composable
    fun renderSportScreen() {
        sportView.renderSportsScreen()
    }

    fun refreshSportScreen() {
        sportView.refreshSportsView()
    }

    companion object {
        private var instance : SportSDK? = null

        fun getInstance(): SportSDK {
            if (instance == null)
                instance = SportSDK()
            return instance!!
        }
    }
}