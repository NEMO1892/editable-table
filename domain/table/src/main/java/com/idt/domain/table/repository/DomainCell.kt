package com.idt.domain.table.repository

data class DomainCell(
    val rowIndex: Int,
    val columnIndex: Int,
    val text: String,
    val isGreen: Boolean
)
