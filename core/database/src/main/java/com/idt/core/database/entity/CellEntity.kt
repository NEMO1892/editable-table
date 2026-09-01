package com.idt.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "cell",
    primaryKeys = ["rowId", "columnIndex"],
    foreignKeys = [
        ForeignKey(
            entity = TableRowEntity::class,
            parentColumns = ["id"],
            childColumns = ["rowId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("rowId")]
)
data class CellEntity(
    val rowId: Int,
    val columnIndex: Int,
    val text: String,
    val isGreen: Boolean
)
