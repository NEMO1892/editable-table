package com.idt.domain.table.use_case

import com.idt.domain.table.model.NumberOfColumns
import com.idt.domain.table.repository.TableRepository
import javax.inject.Inject

class UpdateNumberOfColumnsUseCase @Inject constructor(
    private val tableRepository: TableRepository
) {

    suspend operator fun invoke(numberOfColumns: NumberOfColumns?) = tableRepository.updateNumberOfColumns(numberOfColumns)
}
