package com.idt.ui.table.impl

import androidx.lifecycle.ViewModel
import com.idt.domain.table.model.NumberOfColumns
import com.idt.domain.table.model.NumberOfRows
import com.idt.domain.table.use_case.GetTableInfoUseCase
import com.idt.ui.table.impl.mapper.TableRowMapper
import com.idt.ui.table.impl.model.TableEvent
import com.idt.ui.table.impl.model.TableState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TableViewModel @Inject constructor(
    private val getTableInfoUseCase: GetTableInfoUseCase,
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
        val rows = getTableInfoUseCase(
            numberOfRows = NumberOfRows(numberOfRows),
            numberOfColumns = NumberOfColumns(numberOfColumns)
        )
        _state.update { state ->
            state.copy(
                rows = rows.map(tableRowMapper::invoke),
                editableCellId = null
            )
        }
    }

    private fun handleOnCellDoubleClicked(id: Int) {
        _state.update { state -> state.copy(editableCellId = id) }
    }

    private fun handleOnCellEditingFinished() {
        _state.update { state -> state.copy(editableCellId = null) }
    }

    private fun handleOnCellClicked(id: Int) {
        _state.update { state ->
            state.copy(
                editableCellId = null,
                rows = state.rows.map { row ->
                    if (row.cells.none { cell -> cell.id == id }) return@map row
                    row.copy(
                        cells = row.cells.map { cell ->
                            if (cell.id == id) cell.copy(isGreen = !cell.isGreen) else cell
                        }
                    )
                }
            )
        }
    }

    private fun handleOnCellTextChanged(cellText: String, id: Int) {
        _state.update { state ->
            state.copy(
                rows = state.rows.map { row ->
                    if (row.cells.none { cell -> cell.id == id }) return@map row
                    row.copy(
                        cells = row.cells.map { cell ->
                            if (cell.id == id) cell.copy(text = cellText) else cell
                        }
                    )
                }
            )
        }
    }
}
