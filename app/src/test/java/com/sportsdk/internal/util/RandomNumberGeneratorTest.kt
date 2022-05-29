package com.sportsdk.internal.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RandomNumberGeneratorTest {

    private val randomNumberGenerator = RandomNumberGenerator()

    @Test fun `retrieveTransactions - no errors - returns correct output`() {
        randomNumberGenerator.getRandomNumber().run {
            assertThat(this).isGreaterThan(-1)
            assertThat(this).isLessThan(9)
        }
    }
}