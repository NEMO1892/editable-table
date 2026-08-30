package com.idt.domain.home.use_case

import com.idt.domain.home.model.NumberOfColumns
import com.idt.domain.home.model.NumberOfRows
import com.idt.domain.home.model.ValidationResult
import com.idt.domain.home.repository.HomeRepository
import javax.inject.Inject

class ValidateTableSizeUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    operator fun invoke(
        numberOfRows: NumberOfRows,
        numberOfColumns: NumberOfColumns
    ): ValidationResult = homeRepository.validateTableSize(numberOfRows, numberOfColumns)
}
