package com.idt.ui.home.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.idt.ui.home.compose.HomeScreen
import com.idt.ui.home_api.HomeKey
import com.idt.ui.table.api.TableKey

fun EntryProviderScope<NavKey>.homeEntryBuilder(onNavigateToTable: (TableKey) -> Unit) {
    entry<HomeKey> {
        HomeScreen(onNavigateToTable = onNavigateToTable)
    }
}
