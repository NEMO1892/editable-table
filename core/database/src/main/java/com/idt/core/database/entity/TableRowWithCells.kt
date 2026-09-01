package com.idt.core.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TableRowWithCells(
    @Embedded
    val row: TableRowEntity,
    @Relation(parentColumn = "id", entityColumn = "rowId")
    val cells: List<CellEntity>,
)
