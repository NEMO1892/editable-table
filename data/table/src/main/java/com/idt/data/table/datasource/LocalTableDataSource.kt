package com.idt.data.table.datasource

import com.idt.core.common.coroutines.DefaultDispatcher
import com.idt.core.common.coroutines.IoDispatcher
import com.idt.core.data_store.TableSizeDataStoreManager
import com.idt.core.database.dao.TableDao
import com.idt.data.table.mapper.CellEntityMapper
import com.idt.data.table.mapper.TableRowEntityMapper
import com.idt.data.table.mapper.TableRowMapper
import com.idt.domain.table.model.NumberOfColumns
import com.idt.domain.table.model.NumberOfRows
import com.idt.domain.table.repository.RowsAndColumns
import com.idt.domain.table.repository.TableRow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class LocalTableDataSource @Inject constructor(
    private val tablePopulatorManager: TablePopulatorManager,
    private val tableSizeDataStoreManager: TableSizeDataStoreManager,
    private val tableDao: TableDao,
    private val tableRowMapper: TableRowMapper,
    private val tableRowEntityMapper: TableRowEntityMapper,
    private val cellEntityMapper: CellEntityMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : TableDataSource {

    override fun getNumberOfRowsAndColumns(): Flow<RowsAndColumns> = combine(
        tableSizeDataStoreManager.getNumberOfRows(),
        tableSizeDataStoreManager.getNumberOfColumns()
    ) { numberOfRows, numberOfColumns ->
        RowsAndColumns(
            numberOfRows?.let(::NumberOfRows),
            numberOfColumns?.let(::NumberOfColumns)
        )
    }.flowOn(ioDispatcher)

    override suspend fun updateNumberOfRows(numberOfRows: NumberOfRows?) {
        withContext(ioDispatcher) {
            tableSizeDataStoreManager.setNumberOfRows(numberOfRows?.rows)
        }
    }

    override suspend fun updateNumberOfColumns(numberOfColumns: NumberOfColumns?) {
        withContext(ioDispatcher) {
            tableSizeDataStoreManager.setNumberOfColumns(numberOfColumns?.columns)
        }
    }

    override suspend fun getTableInfo(
        numberOfRows: NumberOfRows,
        numberOfColumns: NumberOfColumns
    ): List<TableRow> = withContext(defaultDispatcher) {
        val cachedCells = tableDao.getTableInfo()
            .map(tableRowMapper::invoke)
            .flatMap(TableRow::domainCells)
            .associateBy { domainCell -> domainCell.rowIndex to domainCell.columnIndex }

        val populatedTable = tablePopulatorManager.populateTableWithRandomData(
            numberOfRows = numberOfRows,
            numberOfColumns = numberOfColumns
        )

        val table = populatedTable.map { tableRow ->
            tableRow.copy(
                domainCells = tableRow.domainCells.map { domainCell ->
                    cachedCells[domainCell.rowIndex to domainCell.columnIndex] ?: domainCell
                }
            )
        }

        cacheMissingParts(table)
        table
    }

    override suspend fun updateCellColor(rowIndex: Int, columnIndex: Int, isGreen: Boolean) {
        withContext(ioDispatcher) {
            tableDao.updateCellColor(rowId = rowIndex, columnIndex = columnIndex, isGreen = isGreen)
        }
    }

    override suspend fun updateCellText(rowIndex: Int, columnIndex: Int, text: String) {
        withContext(ioDispatcher) {
            tableDao.updateCellText(rowId = rowIndex, columnIndex = columnIndex, text = text)
        }
    }

    private suspend fun cacheMissingParts(tableRows: List<TableRow>) {
        tableDao.insertMissingTableParts(
            tableRowEntities = tableRows.map(tableRowEntityMapper::invoke),
            cellEntities = tableRows
                .flatMap(TableRow::domainCells)
                .map { domainCell -> cellEntityMapper(domainCell) }
        )
    }
}
