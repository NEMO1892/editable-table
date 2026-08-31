package com.idt.ui.table.impl

import androidx.lifecycle.ViewModel
import com.idt.ui.table.impl.model.TableEvent
import com.idt.ui.table.impl.model.TableState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TableViewModel @Inject constructor(
) : ViewModel() {

    private val _state = MutableStateFlow(TableState())
    val state: StateFlow<TableState> = _state.asStateFlow()

    internal fun handleUserEvent(event: TableEvent) {
        when (event) {
            is TableEvent.OnGetTable -> handleOnGetTable(event.numberOfRows, event.numberOfColumns)
        }
    }

    private fun handleOnGetTable(numberOfRows: Int, numberOfColumns: Int) {
        // TODO: populate table
    }
}