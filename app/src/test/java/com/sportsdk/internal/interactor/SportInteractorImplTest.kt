package com.sportsdk.internal.interactor

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.sportsdk.internal.domain.Sport
import com.sportsdk.internal.repository.ContentRepository
import com.sportsdk.internal.util.RandomNumberGenerator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertThrows
import org.junit.Test

class SportInteractorImplTest {

    private val contentRepository = mock<ContentRepository>()
    private val randomNumberGenerator = mock<RandomNumberGenerator>()

    private val interactor = SportInteractorImpl(contentRepository, randomNumberGenerator)

    @Test fun `getSport - no errors - returns correct output`() = runTest {
        whenever(randomNumberGenerator.getRandomNumber()).thenReturn(2)
        whenever(contentRepository.getFeaturedSports()).thenReturn(
            listOf(
                Sport(
                    "Cycling",
                    "Cycling, also called bicycling or biking, is the use of bicycles for transport, recreation, exercise or sport. People engaged in cycling are referred to as \"cyclists\", \"bicyclists\", or \"bikers\". Apart from two-wheeled bicycles, \"cycling\" also includes the riding of unicycles, tricycles, quadricycles, recumbent and similar human-powered vehicles (HPVs)."
                ),
                Sport(
                    "Association football",
                    "Association football, more commonly known as simply football or soccer,[a] is a team sport played with a spherical ball between two teams of 11 players. It is played by approximately 250 million players in over 200 countries and dependencies, making it the world's most popular sport."
                ),
                Sport(
                    "Hockey",
                    "Hockey is a term used to denote various types of both summer and winter team sports which originated on either an outdoor field, sheet of ice, or dry floor such as in a gymnasium.\n" +
                            "There are many types of hockey. Some games make the use of skates, either wheeled, or bladed while others do not. In order to help make the distinction between these various games, the word \"hockey\" is often preceded by another word i.e. \"field hockey\", \"ice hockey\", \"roller hockey\", \"rink hockey\", or \"floor hockey\". "
                )
            )
        )

        assertThat(interactor.getSport()).isEqualTo(Sport(
            "Hockey",
            "Hockey is a term used to denote various types of both summer and winter team sports which originated on either an outdoor field, sheet of ice, or dry floor such as in a gymnasium.\n" +
                    "There are many types of hockey. Some games make the use of skates, either wheeled, or bladed while others do not. In order to help make the distinction between these various games, the word \"hockey\" is often preceded by another word i.e. \"field hockey\", \"ice hockey\", \"roller hockey\", \"rink hockey\", or \"floor hockey\". "
        ))
        verify(randomNumberGenerator).getRandomNumber()
        verify(contentRepository).getFeaturedSports()
    }

    @ExperimentalCoroutinesApi
    @Test fun `retrieveTransactions - errors - returns exception`() {
        assertThrows(Exception::class.java) {
            runTest {
                whenever(randomNumberGenerator.getRandomNumber()).thenReturn(12)
                whenever(contentRepository.getFeaturedSports()).thenReturn(
                    listOf(
                        Sport(
                            "Cycling",
                            "Cycling, also called bicycling or biking, is the use of bicycles for transport, recreation, exercise or sport. People engaged in cycling are referred to as \"cyclists\", \"bicyclists\", or \"bikers\". Apart from two-wheeled bicycles, \"cycling\" also includes the riding of unicycles, tricycles, quadricycles, recumbent and similar human-powered vehicles (HPVs)."
                        ),
                        Sport(
                            "Association football",
                            "Association football, more commonly known as simply football or soccer,[a] is a team sport played with a spherical ball between two teams of 11 players. It is played by approximately 250 million players in over 200 countries and dependencies, making it the world's most popular sport."
                        ),
                        Sport(
                            "Hockey",
                            "Hockey is a term used to denote various types of both summer and winter team sports which originated on either an outdoor field, sheet of ice, or dry floor such as in a gymnasium.\n" +
                                    "There are many types of hockey. Some games make the use of skates, either wheeled, or bladed while others do not. In order to help make the distinction between these various games, the word \"hockey\" is often preceded by another word i.e. \"field hockey\", \"ice hockey\", \"roller hockey\", \"rink hockey\", or \"floor hockey\". "
                        )
                    )
                )
                interactor.getSport()

                verify(randomNumberGenerator).getRandomNumber()
                verify(contentRepository).getFeaturedSports()
            }
        }
    }
}