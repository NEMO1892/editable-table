package com.idt.ui.home.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.idt.core.design_system.theme.EditabletableTheme
import com.idt.ui.home.HomeEvent
import com.idt.ui.home.HomeState
import com.idt.ui.home.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreenContent(
        state = state,
        onUserEvent = viewModel::handleEvent,
        modifier = modifier
    )
}

@Composable
private fun HomeScreenContent(
    state: HomeState,
    onUserEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .imePadding()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        HomeBody(
            numberOfRows = state.numberOfRows,
            numberOfColumns = state.numberOfColumns,
            onNumberOfRowsChanged = { numberOfRows ->
                onUserEvent(HomeEvent.OnNumberOfRowsChanged(numberOfRows))
            },
            onNumberOfColumnsChanged = { numberOfColumns ->
                onUserEvent(HomeEvent.OnNumberOfColumnsChanged(numberOfColumns))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
@PreviewScreenSizes
private fun HomeScreenContentPreview() {
    EditabletableTheme {
        HomeScreenContent(
            state = HomeState(),
            onUserEvent = {  }
        )
    }
}
