package com.idt.domain.table.use_case

import com.idt.domain.table.model.NumberOfColumns
import com.idt.domain.table.model.NumberOfRows
import com.idt.domain.table.model.ValidationResult
import com.idt.domain.table.repository.TableRepository
import javax.inject.Inject

class ValidateTableSizeUseCase @Inject constructor(
    private val tableRepository: TableRepository
) {

    operator fun invoke(
        numberOfRows: NumberOfRows,
        numberOfColumns: NumberOfColumns
    ): ValidationResult = tableRepository.validateTableSize(numberOfRows, numberOfColumns)
}
