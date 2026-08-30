package com.idt.ui.home

import androidx.compose.runtime.Immutable

@Immutable
data class HomeState(
    val numberOfRows: String = "",
    val numberOfColumns: String = "",
)
