package com.idt.domain.table.use_case

import com.idt.domain.table.model.NumberOfColumns
import com.idt.domain.table.model.NumberOfRows
import com.idt.domain.table.repository.TableRepository
import com.idt.domain.table.repository.TableRow
import javax.inject.Inject

class GetTableInfoUseCase @Inject constructor(
    private val tableRepository: TableRepository
) {

    suspend operator fun invoke(
        numberOfRows: NumberOfRows,
        numberOfColumns: NumberOfColumns
    ): List<TableRow> = tableRepository.getTableInfo(numberOfRows, numberOfColumns)
}
