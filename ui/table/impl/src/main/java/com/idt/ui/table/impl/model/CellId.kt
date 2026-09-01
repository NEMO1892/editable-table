package com.idt.ui.table.impl.model

import androidx.compose.runtime.Immutable

@Immutable
data class CellId(
    val rowIndex: Int,
    val columnIndex: Int,
)
