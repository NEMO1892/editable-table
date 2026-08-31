package com.idt.data.table.datasource

import com.idt.domain.table.model.NumberOfColumns
import com.idt.domain.table.model.NumberOfRows
import com.idt.domain.table.repository.RowsAndColumns
import com.idt.domain.table.repository.TableRow
import kotlinx.coroutines.flow.Flow

internal interface TableDataSource {

    fun getNumberOfRowsAndColumns(): Flow<RowsAndColumns>

    suspend fun updateNumberOfRows(numberOfRows: NumberOfRows?)

    suspend fun updateNumberOfColumns(numberOfColumns: NumberOfColumns?)

    fun getTableInfo(
        numberOfRows: NumberOfRows,
        numberOfColumns: NumberOfColumns
    ): List<TableRow>
}
