package com.idt.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    internal fun handleEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnNumberOfColumnsChanged -> handleOnNumberOfColumnsChanged(event.text)
            is HomeEvent.OnNumberOfRowsChanged -> handleOnNumberOfRowsChanged(event.text)
        }
    }

    private fun handleOnNumberOfColumnsChanged(numberOfColumns: String) {
        _state.update { it.copy(numberOfColumns = numberOfColumns) }
    }

    private fun handleOnNumberOfRowsChanged(numberOfRows: String) {
        _state.update { it.copy(numberOfRows = numberOfRows) }
    }
}
