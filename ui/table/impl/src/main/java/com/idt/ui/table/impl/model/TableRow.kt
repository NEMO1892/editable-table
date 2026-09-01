package com.idt.ui.table.impl.model

import androidx.compose.runtime.Immutable

@Immutable
data class TableRow(
    val id: Int,
    val cells: List<Cell>,
)
