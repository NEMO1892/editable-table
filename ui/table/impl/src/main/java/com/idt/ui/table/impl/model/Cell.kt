package com.idt.ui.table.impl.model

import androidx.compose.runtime.Immutable

@Immutable
data class Cell(
    val id: Int,
    val text: String,
    val isGreen: Boolean
)
