package com.idt.ui.table.impl.model

import androidx.compose.runtime.Immutable

@Immutable
data class TableState(
    val rows: List<TableRow> = emptyList(),
)
