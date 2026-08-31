package com.idt.ui.home.model

sealed interface HomeEvent {

    data class OnNumberOfRowsChanged(val text: String) : HomeEvent

    data class OnNumberOfColumnsChanged(val text: String) : HomeEvent

    data object OnNextClicked : HomeEvent
}
