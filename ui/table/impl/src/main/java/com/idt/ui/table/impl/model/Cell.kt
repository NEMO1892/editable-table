package com.idt.ui.table.impl.model

import androidx.compose.runtime.Immutable

@Immutable
data class Cell(
    val id: CellId,
    val text: String,
    val isGreen: Boolean
)
