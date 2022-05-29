package com.sportsdk.internal.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sportsdk.R
import com.sportsdk.internal.di.DaggerAppComponent
import javax.inject.Inject

internal class SportView {

    @Inject lateinit var viewModel: SportViewModel

    init {
        DaggerAppComponent.builder().build().inject(this)
    }

    @Composable
    fun renderSportsScreen() {
        MaterialTheme {
            when {
                viewModel.binding.value.loadingVisible -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }
                viewModel.binding.value.contentVisible -> {
                    Column(
                        content = {
                            Text(
                                viewModel.binding.value.sportName,
                                modifier = Modifier.padding(8.dp)
                            )
                            Text(
                                viewModel.binding.value.sportDescription,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    )
                }
                viewModel.binding.value.errorVisible -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = stringResource(R.string.sport_sdk_sport_screen_error))
                    }
                }
            }
        }
    }

    fun refreshSportsView() {
        viewModel.loadSport()
    }
}