package com.idt.domain.table.use_case

import com.idt.domain.table.model.NumberOfRows
import com.idt.domain.table.repository.TableRepository
import javax.inject.Inject

class UpdateNumberOfRowsUseCase @Inject constructor(
    private val tableRepository: TableRepository
) {

    suspend operator fun invoke(numberOfRows: NumberOfRows?) = tableRepository.updateNumberOfRows(numberOfRows)
}
