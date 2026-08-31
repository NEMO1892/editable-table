package com.idt.data.table.datasource

import com.idt.domain.table.model.NumberOfColumns
import com.idt.domain.table.model.NumberOfRows
import com.idt.domain.table.repository.DomainCell
import com.idt.domain.table.repository.TableRow
import javax.inject.Inject

internal class TablePopulatorManager @Inject constructor() {

    fun populateTableWithRandomData(
        numberOfRows: NumberOfRows,
        numberOfColumns: NumberOfColumns
    ): List<TableRow> {
        var nextRowId = 0
        var nextCellId = 0
        return List(numberOfRows.rows) {
            TableRow(
                id = nextRowId++,
                domainCells = List(numberOfColumns.columns) {
                    DomainCell(
                        id = nextCellId++,
                        text = generateRandomText()
                    )
                }
            )
        }
    }

    private fun generateRandomText(): String {
        val length = TEXT_LENGTH_RANGE.random()

        return buildString(length) {
            repeat(length) { append(TEXT_ALPHABET.random()) }
        }
    }

    private companion object {

        val TEXT_LENGTH_RANGE = 1..16
        val TEXT_ALPHABET = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    }
}
