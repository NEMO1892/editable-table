package com.idt.ui.table.impl.model

sealed interface TableEvent {

    data class OnGetTable(val numberOfRows: Int, val numberOfColumns: Int) : TableEvent

    data class OnCellClicked(val id: CellId) : TableEvent

    data class OnCellDoubleClicked(val id: CellId) : TableEvent

    data class OnCellTextChanged(val id: CellId, val text: String) : TableEvent

    data object OnCellEditingFinished : TableEvent
}
