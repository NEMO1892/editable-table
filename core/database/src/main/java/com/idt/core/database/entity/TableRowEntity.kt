package com.idt.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_row")
data class TableRowEntity(
    @PrimaryKey
    val id: Int,
)
