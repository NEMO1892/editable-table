package com.idt.ui.table.impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.idt.ui.table.api.TableKey
import com.idt.ui.table.impl.compose.TableScreen

fun EntryProviderScope<NavKey>.tableEntryBuilder() {
    entry<TableKey> { key ->
        TableScreen(
            numberOfRows = key.numberOfRows,
            numberOfColumns = key.numberOfColumns
        )
    }
}
