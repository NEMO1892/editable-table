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
        }
    }

    private fun handleOnGetTable(numberOfRows: Int, numberOfColumns: Int) {
        val rows = getTableInfoUseCase(
            numberOfRows = NumberOfRows(numberOfRows),
            numberOfColumns = NumberOfColumns(numberOfColumns)
        )

        _state.update { state -> state.copy(rows = rows.map(tableRowMapper::invoke)) }
    }
}
