package com.idt.ui.table.api

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface TableNavKey : NavKey

@Serializable
data class TableKey(
    val numberOfRows: Int,
    val numberOfColumns: Int
) : TableNavKey
