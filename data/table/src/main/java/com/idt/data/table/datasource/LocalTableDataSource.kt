package com.idt.data.table.datasource

import com.idt.core.data_store.TableSizeDataStoreManager
import com.idt.domain.table.model.NumberOfColumns
import com.idt.domain.table.model.NumberOfRows
import com.idt.domain.table.repository.RowsAndColumns
import com.idt.domain.table.repository.TableRow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

internal class LocalTableDataSource @Inject constructor(
    private val tablePopulatorManager: TablePopulatorManager,
    private val tableSizeDataStoreManager: TableSizeDataStoreManager
) : TableDataSource {

    override fun getNumberOfRowsAndColumns(): Flow<RowsAndColumns> = combine(
        tableSizeDataStoreManager.getNumberOfRows(),
        tableSizeDataStoreManager.getNumberOfColumns()
    ) { numberOfRows, numberOfColumns ->
        RowsAndColumns(
            numberOfRows?.let(::NumberOfRows),
            numberOfColumns?.let(::NumberOfColumns)
        )
    }

    override suspend fun updateNumberOfRows(numberOfRows: NumberOfRows?) {
        tableSizeDataStoreManager.setNumberOfRows(numberOfRows?.rows)
    }

    override suspend fun updateNumberOfColumns(numberOfColumns: NumberOfColumns?) {
        tableSizeDataStoreManager.setNumberOfColumns(numberOfColumns?.columns)
    }

    override fun getTableInfo(
        numberOfRows: NumberOfRows,
        numberOfColumns: NumberOfColumns
    ): List<TableRow> = tablePopulatorManager.populateTableWithRandomData(
        numberOfRows = numberOfRows,
        numberOfColumns = numberOfColumns
    )
}
