package com.idt.data.table.datasource

import com.idt.domain.table.model.NumberOfColumns
import com.idt.domain.table.model.NumberOfRows
import com.idt.domain.table.repository.TableRow

internal interface TableDataSource {

    fun getTableInfo(
        numberOfRows: NumberOfRows,
        numberOfColumns: NumberOfColumns
    ): List<TableRow>
}
