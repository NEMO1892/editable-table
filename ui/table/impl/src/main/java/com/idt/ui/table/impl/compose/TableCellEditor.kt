package com.idt.ui.table.impl.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.idt.ui.table.impl.model.CellId

@Composable
internal fun TableCellEditor(
    cellId: CellId,
    text: String,
    onTextChanged: (String) -> Unit,
    onEditingFinished: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var value: TextFieldValue by remember(cellId) {
        mutableStateOf(TextFieldValue(text = text, selection = TextRange(text.length)))
    }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(cellId) {
        focusRequester.requestFocus()
    }

    BasicTextField(
        value = value,
        onValueChange = { newValue ->
            value = newValue
            onTextChanged(newValue.text)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { onEditingFinished() }),
        decorationBox = { innerTextField ->
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
                    .border(width = 2.dp, color = MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 4.dp)
            ) {
                innerTextField()
            }
        },
        modifier = modifier.focusRequester(focusRequester),
    )
}
