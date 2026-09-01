package com.idt.domain.table.use_case

import com.idt.domain.table.repository.TableRepository
import javax.inject.Inject

class UpdateCellColorUseCase @Inject constructor(
    private val tableRepository: TableRepository
) {

    suspend operator fun invoke(rowIndex: Int, columnIndex: Int, isGreen: Boolean) =
        tableRepository.updateCellColor(
            rowIndex,
            columnIndex,
            isGreen
        )
}
