package com.idt.ui.home

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
data class HomeState(
    val numberOfRows: String = "",
    @StringRes val errorTextNumberOfRows: Int? = null,
    val numberOfColumns: String = "",
    @StringRes val errorTextNumberOfColumns: Int? = null,
)
