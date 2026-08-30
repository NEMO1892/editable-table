package com.idt.data.home.repository

import com.idt.data.home.validator.TableSizeValidator
import com.idt.domain.home.model.NumberOfColumns
import com.idt.domain.home.model.NumberOfRows
import com.idt.domain.home.model.ValidationResult
import com.idt.domain.home.repository.HomeRepository
import javax.inject.Inject

internal class HomeRepositoryImpl @Inject constructor(
    private val tableSizeValidator: TableSizeValidator
) : HomeRepository {

    override fun validateTableSize(
        numberOfRows: NumberOfRows,
        numberOfColumns: NumberOfColumns
    ): ValidationResult = tableSizeValidator.validate(numberOfRows, numberOfColumns)
}
