package com.idt.domain.table.use_case

import com.idt.domain.table.repository.RowsAndColumns
import com.idt.domain.table.repository.TableRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNumberOfRowsAndColumnsUseCase @Inject constructor(
    private val tableRepository: TableRepository
) {

    operator fun invoke(): Flow<RowsAndColumns> = tableRepository.getNumberOfRowsAndColumns()
}
