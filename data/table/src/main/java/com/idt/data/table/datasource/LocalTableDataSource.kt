package com.idt.data.table.datasource

import com.idt.domain.table.model.NumberOfColumns
import com.idt.domain.table.model.NumberOfRows
import com.idt.domain.table.repository.TableRow
import javax.inject.Inject

internal class LocalTableDataSource @Inject constructor(
    private val tablePopulatorManager: TablePopulatorManager
) : TableDataSource {

    override fun getTableInfo(
        numberOfRows: NumberOfRows,
        numberOfColumns: NumberOfColumns
    ): List<TableRow> = tablePopulatorManager.populateTableWithRandomData(
        numberOfRows = numberOfRows,
        numberOfColumns = numberOfColumns
    )
}
