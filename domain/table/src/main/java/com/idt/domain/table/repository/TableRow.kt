package com.idt.domain.table.repository

data class TableRow(
    val id: Int,
    val domainCells: List<DomainCell>,
)
