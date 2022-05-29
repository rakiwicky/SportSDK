package com.sportsdk.internal.ui

import com.google.common.truth.Truth.assertThat
import com.sportsdk.internal.domain.Sport
import com.sportsdk.internal.ui.SportViewState.TargetState.*
import org.junit.Test

class SportViewStateTest {

    private val viewState = SportViewState()

    @Test fun `verify - initial state`() {
        viewState.binding.value.run {
            assertThat(sportName).isEqualTo("")
            assertThat(sportDescription).isEqualTo("")
            assertThat(loadingVisible).isFalse()
            assertThat(contentVisible).isFalse()
            assertThat(errorVisible).isFalse()
        }
    }

    @Test fun `moveTo - loading - updates correctly`() {
        viewState.moveTo(Loading)

        viewState.binding.value.run {
            assertThat(loadingVisible).isTrue()
            assertThat(contentVisible).isFalse()
            assertThat(errorVisible).isFalse()
        }
    }

    @Test fun `moveTo - content - updates correctly`() {
        val sport = Sport("name", "desc")
        viewState.moveTo(Content(sport))

        viewState.binding.value.run {
            assertThat(sportName).isEqualTo(sport.name)
            assertThat(sportDescription).isEqualTo(sport.description)
            assertThat(loadingVisible).isFalse()
            assertThat(contentVisible).isTrue()
            assertThat(errorVisible).isFalse()
        }
    }

    @Test fun `moveTo - error - updates correctly`() {
        viewState.moveTo(Error)

        viewState.binding.value.run {
            assertThat(loadingVisible).isFalse()
            assertThat(contentVisible).isFalse()
            assertThat(errorVisible).isTrue()
        }
    }
}