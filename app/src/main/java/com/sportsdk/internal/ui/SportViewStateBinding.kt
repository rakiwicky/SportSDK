package com.sportsdk.internal.ui

internal data class SportViewStateBinding(
    val sportName: String,
    val sportDescription: String,
    val contentVisible: Boolean,
    val errorVisible: Boolean,
    val loadingVisible: Boolean
)