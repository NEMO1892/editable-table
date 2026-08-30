package com.idt.ui.home.compose

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.idt.core.design_system.theme.EditabletableTheme
import com.idt.ui.home.impl.R

@Composable
internal fun HomeBody(
    numberOfRows: String,
    numberOfColumns: String,
    onNumberOfRowsChanged: (String) -> Unit,
    onNumberOfColumnsChanged: (String) -> Unit,
    onNextClicked: () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes errorTextNumberOfRows: Int? = null,
    @StringRes errorTextNumberOfColumns: Int? = null
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        OutlinedTextField(
            value = numberOfRows,
            onValueChange = { onNumberOfRowsChanged(it) },
            placeholder = { Text(stringResource(R.string.home_rows_placeholder)) },
            label = { Text(stringResource(R.string.home_rows_label)) },
            singleLine = true,
            isError = errorTextNumberOfRows != null,
            supportingText = errorTextNumberOfRows?.let { errorText ->
                { Text(stringResource(errorText)) }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )

        OutlinedTextField(
            value = numberOfColumns,
            onValueChange = { onNumberOfColumnsChanged(it) },
            placeholder = { Text(stringResource(R.string.home_columns_placeholder)) },
            label = { Text(stringResource(R.string.home_columns_label)) },
            singleLine = true,
            isError = errorTextNumberOfColumns != null,
            supportingText = errorTextNumberOfColumns?.let { errorText ->
                { Text(stringResource(errorText)) }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { onNextClicked() })
        )

        Button(onClick = onNextClicked) {
            Text(stringResource(R.string.home_next))
        }
    }
}

@Composable
@Preview(showBackground = true)
@PreviewScreenSizes
private fun HomeBodyPreview() {
    EditabletableTheme {
        HomeBody(
            numberOfRows = "",
            numberOfColumns = "",
            onNumberOfRowsChanged = {},
            onNumberOfColumnsChanged = {},
            onNextClicked = {}
        )
    }
}
