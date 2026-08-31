package com.idt.domain.table.repository

import com.idt.domain.table.model.NumberOfColumns
import com.idt.domain.table.model.NumberOfRows
import com.idt.domain.table.model.ValidationResult
import kotlinx.coroutines.flow.Flow

typealias RowsAndColumns = Pair<NumberOfRows?, NumberOfColumns?>

interface TableRepository {

    fun getNumberOfRowsAndColumns(): Flow<RowsAndColumns>

    suspend fun updateNumberOfRows(numberOfRows: NumberOfRows?)

    suspend fun updateNumberOfColumns(numberOfColumns: NumberOfColumns?)

    fun validateTableSize(
        numberOfRows: NumberOfRows,
        numberOfColumns: NumberOfColumns
    ): ValidationResult

    fun getTableInfo(
        numberOfRows: NumberOfRows,
        numberOfColumns: NumberOfColumns
    ): List<TableRow>
}
