package com.idt.ui.table.impl.model

sealed interface TableEvent {

    data class OnGetTable(val numberOfRows: Int, val numberOfColumns: Int) : TableEvent
}
