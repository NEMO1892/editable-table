package com.idt.domain.table.model

sealed class ValidationResult {

    data object Success : ValidationResult()

    data class Error(
        val isNumberOfRowsInvalid: Boolean,
        val isNumberOfColumnsInvalid: Boolean
    ) : ValidationResult()
}
