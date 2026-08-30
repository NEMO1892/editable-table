package com.idt.ui.home

import androidx.lifecycle.ViewModel
import com.idt.domain.home.model.NumberOfColumns
import com.idt.domain.home.model.NumberOfRows
import com.idt.domain.home.model.ValidationResult
import com.idt.domain.home.use_case.ValidateTableSizeUseCase
import com.idt.ui.home.impl.R
import com.idt.ui.table.api.TableKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val validateTableSizeUseCase: ValidateTableSizeUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _navigateToTable = Channel<TableKey>(Channel.BUFFERED)
    val navigateToTable: Flow<TableKey> = _navigateToTable.receiveAsFlow()

    internal fun handleEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnNumberOfColumnsChanged -> handleOnNumberOfColumnsChanged(event.text)
            is HomeEvent.OnNumberOfRowsChanged -> handleOnNumberOfRowsChanged(event.text)
            is HomeEvent.OnNextClicked -> handleOnNextClicked()
        }
    }

    private fun handleOnNumberOfColumnsChanged(numberOfColumns: String) {
        _state.update {
            it.copy(
                numberOfColumns = numberOfColumns,
                errorTextNumberOfColumns = null,
            )
        }
    }

    private fun handleOnNumberOfRowsChanged(numberOfRows: String) {
        _state.update {
            it.copy(
                numberOfRows = numberOfRows,
                errorTextNumberOfRows = null,
            )
        }
    }

    private fun handleOnNextClicked() {
        val numberOfRows = _state.value.numberOfRows.toIntOrNull() ?: 0
        val numberOfColumns = _state.value.numberOfColumns.toIntOrNull() ?: 0
        val result = validateTableSizeUseCase(
            numberOfRows = NumberOfRows(numberOfRows),
            numberOfColumns = NumberOfColumns(numberOfColumns)
        )

        when (result) {
            is ValidationResult.Success -> {
                _state.update {
                    it.copy(
                        errorTextNumberOfRows = null,
                        errorTextNumberOfColumns = null,
                    )
                }

                _navigateToTable.trySend(
                    TableKey(numberOfRows = numberOfRows, numberOfColumns = numberOfColumns)
                )
            }

            is ValidationResult.Error -> _state.update {
                it.copy(
                    errorTextNumberOfRows = R.string.home_rows_error.takeIf { result.isNumberOfRowsInvalid },
                    errorTextNumberOfColumns = R.string.home_columns_error.takeIf { result.isNumberOfColumnsInvalid },
                )
            }

        }
    }
}
