package com.sportsdk.internal.di

import com.sportsdk.internal.interactor.SportInteractor
import com.sportsdk.internal.interactor.SportInteractorImpl
import com.sportsdk.internal.repository.ContentRepository
import com.sportsdk.internal.repository.ContentRepositoryImpl
import com.sportsdk.internal.ui.SportView
import com.sportsdk.internal.util.RandomNumberGenerator
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class MainModule {

    @Binds
    internal abstract fun bindRepository(impl: ContentRepositoryImpl): ContentRepository

    @Binds
    internal abstract fun bindInteractor(impl: SportInteractorImpl): SportInteractor

    companion object {

        @Provides
        @Singleton
        internal fun provideSportView(): SportView {
            return SportView()
        }

        @Provides
        @Singleton
        internal fun provideRandomNumberGenerator(): RandomNumberGenerator {
            return RandomNumberGenerator()
        }
    }
}