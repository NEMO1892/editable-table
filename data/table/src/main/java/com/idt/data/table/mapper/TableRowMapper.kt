package com.idt.data.table.mapper

import com.idt.core.database.entity.CellEntity
import com.idt.core.database.entity.TableRowWithCells
import com.idt.domain.table.repository.DomainCell
import com.idt.domain.table.repository.TableRow
import javax.inject.Inject

internal class TableRowMapper @Inject constructor() {

    operator fun invoke(unmapped: TableRowWithCells): TableRow = with(unmapped) {
        TableRow(
            id = row.id,
            domainCells = cells
                .sortedBy { cell -> cell.columnIndex }
                .map { entity -> entity.mapToDomain() }
        )
    }

    private fun CellEntity.mapToDomain(): DomainCell = DomainCell(
        rowIndex = rowId,
        columnIndex = columnIndex,
        text = text,
        isGreen = isGreen
    )
}
