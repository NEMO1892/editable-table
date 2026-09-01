package com.idt.data.table.mapper

import com.idt.core.database.entity.CellEntity
import com.idt.domain.table.repository.DomainCell
import javax.inject.Inject

internal class CellEntityMapper @Inject constructor() {

    operator fun invoke(unmapped: DomainCell): CellEntity = with(unmapped) {
        CellEntity(
            rowId = rowIndex,
            columnIndex = columnIndex,
            text = text,
            isGreen = isGreen
        )
    }
}
