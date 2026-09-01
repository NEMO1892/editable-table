package com.idt.ui.table.impl.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.idt.core.design_system.theme.EditabletableTheme
import com.idt.ui.table.impl.model.Cell
import com.idt.ui.table.impl.model.CellId
import com.idt.ui.table.impl.model.TableEvent
import com.idt.ui.table.impl.model.TableRow
import com.idt.ui.table.impl.model.TableState

@Composable
internal fun TableGrid(
    state: TableState,
    onUserEvent: (TableEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 36.dp, horizontal = 16.dp)
    ) {
        itemsIndexed(
            items = state.rows,
            key = { _, row -> row.id },
        ) { rowIndex, tableRow ->
            val editableCell =
                tableRow.cells.firstOrNull { cell -> cell.id == state.editableCellId }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Row(modifier = Modifier.fillMaxSize()) {
                    tableRow.cells.forEachIndexed { columnIndex, cell ->
                        TableCell(
                            text = cell.text,
                            drawEndBorder = columnIndex == tableRow.cells.lastIndex,
                            drawBottomBorder = rowIndex == state.rows.lastIndex,
                            isGreen = cell.isGreen,
                            onCellClicked = { onUserEvent(TableEvent.OnCellClicked(cell.id)) },
                            onCellDoubleClicked = { onUserEvent(TableEvent.OnCellDoubleClicked(cell.id)) },
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                        )
                    }
                }

                if (editableCell != null) {
                    TableCellEditor(
                        cellId = editableCell.id,
                        text = editableCell.text,
                        onTextChanged = { text ->
                            onUserEvent(TableEvent.OnCellTextChanged(editableCell.id, text))
                        },
                        onEditingFinished = { onUserEvent(TableEvent.OnCellEditingFinished) },
                        modifier = Modifier.matchParentSize(),
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
@PreviewScreenSizes
private fun TableGridPreview() {
    EditabletableTheme {
        TableGrid(
            state = TableState(
                rows =
                    List(8) { rowIndex ->
                        TableRow(
                            id = rowIndex,
                            cells = List(4) { columnIndex ->
                                Cell(
                                    id = CellId(rowIndex = rowIndex, columnIndex = columnIndex),
                                    text = "${rowIndex}$columnIndex",
                                    isGreen = (rowIndex + columnIndex) % 4 == 0
                                )
                            }
                        )
                    }),
            onUserEvent = { }
        )
    }
}
