package com.sportsdk.internal.util

internal class RandomNumberGenerator(
    private val start: Int = 0,
    private val end: Int = 8
) {

    fun getRandomNumber() = (start..end).random()
}