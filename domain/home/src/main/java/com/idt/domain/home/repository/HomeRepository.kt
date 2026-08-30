package com.idt.domain.home.repository

import com.idt.domain.home.model.NumberOfColumns
import com.idt.domain.home.model.NumberOfRows
import com.idt.domain.home.model.ValidationResult

interface HomeRepository {

    fun validateTableSize(
        numberOfRows: NumberOfRows,
        numberOfColumns: NumberOfColumns
    ): ValidationResult
}