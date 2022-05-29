package com.sportsdk.internal.interactor

import com.sportsdk.internal.domain.Sport

internal interface SportInteractor {

    suspend fun getSport(): Sport
}