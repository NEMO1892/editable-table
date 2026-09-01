package com.idt.ui.table.impl.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
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
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            TableGrid(
                state = state,
                onUserEvent = onUserEvent,
            )
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
