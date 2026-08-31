package com.idt.data.table.repository

import com.idt.data.table.datasource.TableDataSource
import com.idt.data.table.validator.TableSizeValidator
import com.idt.domain.table.model.NumberOfColumns
import com.idt.domain.table.model.NumberOfRows
import com.idt.domain.table.model.ValidationResult
import com.idt.domain.table.repository.RowsAndColumns
import com.idt.domain.table.repository.TableRepository
import com.idt.domain.table.repository.TableRow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class TableRepositoryImpl @Inject constructor(
    private val tableSizeValidator: TableSizeValidator,
    private val tableDataSource: TableDataSource
) : TableRepository {

    override fun getNumberOfRowsAndColumns(): Flow<RowsAndColumns> = tableDataSource.getNumberOfRowsAndColumns()

    override suspend fun updateNumberOfRows(numberOfRows: NumberOfRows?) = tableDataSource.updateNumberOfRows(numberOfRows)

    override suspend fun updateNumberOfColumns(numberOfColumns: NumberOfColumns?) = tableDataSource.updateNumberOfColumns(numberOfColumns)

    override fun validateTableSize(
        numberOfRows: NumberOfRows,
        numberOfColumns: NumberOfColumns
    ): ValidationResult = tableSizeValidator.validate(numberOfRows, numberOfColumns)

    override fun getTableInfo(
        numberOfRows: NumberOfRows,
        numberOfColumns: NumberOfColumns
    ): List<TableRow> = tableDataSource.getTableInfo(numberOfRows, numberOfColumns)
}
