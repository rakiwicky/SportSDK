package com.sportsdk.internal.repository

import com.sportsdk.internal.domain.Sport

internal interface ContentRepository {

    suspend fun getFeaturedSports(): List<Sport>
}