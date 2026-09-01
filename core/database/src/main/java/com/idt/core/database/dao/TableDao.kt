package com.idt.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.idt.core.database.entity.CellEntity
import com.idt.core.database.entity.TableRowEntity
import com.idt.core.database.entity.TableRowWithCells

@Dao
interface TableDao {

    @Transaction
    @Query("SELECT * FROM table_row ORDER BY id")
    suspend fun getTableInfo(): List<TableRowWithCells>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRows(tableRowEntities: List<TableRowEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCells(cellEntities: List<CellEntity>)

    @Transaction
    suspend fun insertMissingTableParts(
        tableRowEntities: List<TableRowEntity>,
        cellEntities: List<CellEntity>
    ) {
        insertRows(tableRowEntities)
        insertCells(cellEntities)
    }

    @Query("UPDATE cell SET isGreen = :isGreen WHERE rowId = :rowId AND columnIndex = :columnIndex")
    suspend fun updateCellColor(
        rowId: Int,
        columnIndex: Int,
        isGreen: Boolean
    )

    @Query("UPDATE cell SET text = :text WHERE rowId = :rowId AND columnIndex = :columnIndex")
    suspend fun updateCellText(
        rowId: Int,
        columnIndex: Int,
        text: String
    )
}
