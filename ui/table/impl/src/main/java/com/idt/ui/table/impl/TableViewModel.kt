package com.idt.ui.table.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idt.domain.table.model.NumberOfColumns
import com.idt.domain.table.model.NumberOfRows
import com.idt.domain.table.use_case.GetTableInfoUseCase
import com.idt.domain.table.use_case.UpdateCellColorUseCase
import com.idt.domain.table.use_case.UpdateCellTextUseCase
import com.idt.ui.table.impl.mapper.TableRowMapper
import com.idt.ui.table.impl.model.Cell
import com.idt.ui.table.impl.model.CellId
import com.idt.ui.table.impl.model.TableEvent
import com.idt.ui.table.impl.model.TableRow
import com.idt.ui.table.impl.model.TableState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableViewModel @Inject constructor(
    private val getTableInfoUseCase: GetTableInfoUseCase,
    private val updateCellColorUseCase: UpdateCellColorUseCase,
    private val updateCellTextUseCase: UpdateCellTextUseCase,
    private val tableRowMapper: TableRowMapper
) : ViewModel() {

    private val _state = MutableStateFlow(TableState())
    val state: StateFlow<TableState> = _state.asStateFlow()

    internal fun handleUserEvent(event: TableEvent) {
        when (event) {
            is TableEvent.OnGetTable -> handleOnGetTable(event.numberOfRows, event.numberOfColumns)
            is TableEvent.OnCellClicked -> handleOnCellClicked(event.id)
            is TableEvent.OnCellDoubleClicked -> handleOnCellDoubleClicked(event.id)
            is TableEvent.OnCellTextChanged -> handleOnCellTextChanged(event.text, event.id)
            is TableEvent.OnCellEditingFinished -> handleOnCellEditingFinished()
        }
    }

    private fun handleOnGetTable(numberOfRows: Int, numberOfColumns: Int) {
        viewModelScope.launch {
            _state.update { state -> state.copy(isLoading = true) }
            val rows = getTableInfoUseCase(
                NumberOfRows(numberOfRows),
                NumberOfColumns(numberOfColumns)
            )
            _state.update { state ->
                state.copy(
                    isLoading = false,
                    rows = rows.map(tableRowMapper::invoke),
                    editableCellId = null
                )
            }
        }
    }

    private fun handleOnCellDoubleClicked(id: CellId) {
        _state.update { state -> state.copy(editableCellId = id) }
    }

    private fun handleOnCellEditingFinished() {
        _state.update { state -> state.copy(editableCellId = null) }
    }

    private fun handleOnCellClicked(id: CellId) {
        val isGreen = _state.value.rows.firstNotNullOfOrNull { row -> row.cells.firstOrNull { cell -> cell.id == id } }?.isGreen ?: return
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    editableCellId = null,
                    rows = state.rows.updateCell(id) { cell -> cell.copy(isGreen = !isGreen) }
                )
            }
            updateCellColorUseCase(
                id.rowIndex,
                id.columnIndex,
                !isGreen
            )
        }
    }

    private fun handleOnCellTextChanged(cellText: String, id: CellId) {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(rows = state.rows.updateCell(id) { cell -> cell.copy(text = cellText) })
            }
            updateCellTextUseCase(
                id.rowIndex,
                id.columnIndex,
                cellText
            )
        }
    }

    private fun List<TableRow>.updateCell(id: CellId, transform: (Cell) -> Cell): List<TableRow> = map { row ->
        if (row.cells.none { cell -> cell.id == id }) return@map row
        row.copy(
            cells = row.cells.map { cell -> if (cell.id == id) transform(cell) else cell }
        )
    }
}
