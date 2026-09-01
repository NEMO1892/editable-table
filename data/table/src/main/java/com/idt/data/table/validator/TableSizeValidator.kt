package com.idt.data.table.validator

import com.idt.domain.table.model.NumberOfColumns
import com.idt.domain.table.model.NumberOfRows
import com.idt.domain.table.model.ValidationResult
import javax.inject.Inject

internal class TableSizeValidator @Inject constructor() {

    fun validate(
        numberOfRows: NumberOfRows,
        numberOfColumns: NumberOfColumns
    ): ValidationResult {
        val isNumberOfRowsInvalid = numberOfRows.rows !in ALLOWED_ROWS
        val isNumberOfColumnsInvalid = numberOfColumns.columns !in ALLOWED_COLUMNS

        return if (isNumberOfRowsInvalid || isNumberOfColumnsInvalid) {
            ValidationResult.Error(
                isNumberOfRowsInvalid = isNumberOfRowsInvalid,
                isNumberOfColumnsInvalid = isNumberOfColumnsInvalid
            )
        } else {
            ValidationResult.Success
        }
    }

    private companion object {

        val ALLOWED_ROWS = 1..1000
        val ALLOWED_COLUMNS = 1..6
    }
}
