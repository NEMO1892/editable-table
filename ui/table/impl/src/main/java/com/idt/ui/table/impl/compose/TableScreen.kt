package com.idt.ui.table.impl.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.idt.core.design_system.theme.EditabletableTheme
import com.idt.ui.table.impl.TableViewModel
import com.idt.ui.table.impl.model.TableEvent
import com.idt.ui.table.impl.model.TableState

@Composable
fun TableScreen(
    numberOfRows: Int,
    numberOfColumns: Int,
    viewModel: TableViewModel = hiltViewModel()
) {
    LaunchedEffect(numberOfRows, numberOfColumns) {
        viewModel.handleUserEvent(TableEvent.OnGetTable(numberOfRows, numberOfColumns))
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    TableScreenContent(
        state = state,
        onUserEvent = viewModel::handleUserEvent
    )
}

@Composable
private fun TableScreenContent(
    state: TableState,
    onUserEvent: (TableEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 36.dp, horizontal = 16.dp)
    ) {
        itemsIndexed(
            items = state.rows,
            key = { _, row -> row.id },
        ) { rowIndex, tableRow ->
            val editableCell = tableRow.cells.firstOrNull { cell -> cell.id == state.editableCellId }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
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
private fun TableScreenContentPreview() {
    EditabletableTheme {
        TableScreenContent(
            state = TableState(),
            onUserEvent = { }
        )
    }
}
