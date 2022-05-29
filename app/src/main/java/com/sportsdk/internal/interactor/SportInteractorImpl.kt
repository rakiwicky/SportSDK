package com.sportsdk.internal.interactor

import com.sportsdk.internal.domain.Sport
import com.sportsdk.internal.repository.ContentRepository
import com.sportsdk.internal.util.RandomNumberGenerator
import javax.inject.Inject

internal class SportInteractorImpl @Inject constructor(
    private val contentRepository: ContentRepository,
    private val randomNumberGenerator: RandomNumberGenerator
) : SportInteractor {

    override suspend fun getSport(): Sport {
        return contentRepository.getFeaturedSports()[randomNumberGenerator.getRandomNumber()]
    }
}