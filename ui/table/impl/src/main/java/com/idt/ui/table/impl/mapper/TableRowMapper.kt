package com.idt.ui.table.impl.mapper

import com.idt.domain.table.repository.DomainCell
import com.idt.ui.table.impl.model.Cell
import com.idt.ui.table.impl.model.CellId
import com.idt.ui.table.impl.model.TableRow
import javax.inject.Inject
import com.idt.domain.table.repository.TableRow as DomainTableRow

class TableRowMapper @Inject constructor() {

    operator fun invoke(unmapped: DomainTableRow): TableRow = with(unmapped) {
        TableRow(
            id = id,
            cells = domainCells.map { domain -> domain.mapToUI() }
        )
    }

    private fun DomainCell.mapToUI(): Cell = Cell(
        id = CellId(rowIndex = rowIndex, columnIndex = columnIndex),
        text = text,
        isGreen = isGreen
    )
}
