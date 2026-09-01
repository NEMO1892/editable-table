package com.idt.domain.table.use_case

import com.idt.domain.table.repository.TableRepository
import javax.inject.Inject

class UpdateCellTextUseCase @Inject constructor(
    private val tableRepository: TableRepository
) {

    suspend operator fun invoke(rowIndex: Int, columnIndex: Int, text: String) =
        tableRepository.updateCellText(
            rowIndex,
            columnIndex,
            text
        )
}
