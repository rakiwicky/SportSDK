package com.sportsdk.internal.di

import com.sportsdk.SportSDK
import com.sportsdk.internal.ui.SportView
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class])
internal interface AppComponent {

    fun inject(sportsView: SportView)
    fun inject(sportSDK: SportSDK)
}