package com.idt.domain.table.repository

import com.idt.domain.table.model.NumberOfColumns
import com.idt.domain.table.model.NumberOfRows
import com.idt.domain.table.model.ValidationResult

interface TableRepository {

    fun validateTableSize(
        numberOfRows: NumberOfRows,
        numberOfColumns: NumberOfColumns
    ): ValidationResult

    fun getTableInfo(
        numberOfRows: NumberOfRows,
        numberOfColumns: NumberOfColumns
    ): List<TableRow>
}
