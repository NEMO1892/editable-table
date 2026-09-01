package com.idt.ui.table.impl.compose

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.idt.core.design_system.gridBorder
import com.idt.core.design_system.optional
import com.idt.core.design_system.theme.EditabletableTheme

@Composable
internal fun TableCell(
    text: String,
    drawEndBorder: Boolean,
    drawBottomBorder: Boolean,
    isGreen: Boolean,
    onCellClicked: () -> Unit,
    onCellDoubleClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .combinedClickable(
                onClick = onCellClicked,
                onDoubleClick = onCellDoubleClicked,
            )
            .optional(
                predicate = { isGreen },
                modifier = Modifier.drawBehind { drawRect(Color.Green) }
            )
            .gridBorder(
                drawEndBorder = drawEndBorder,
                drawBottomBorder = drawBottomBorder,
            )
    ) {
        Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(4.dp),
        )
    }
}

@Composable
@Preview(showBackground = true)
@PreviewScreenSizes
private fun TableCellPreview() {
    EditabletableTheme {
        TableCell(
            text = "test",
            drawEndBorder = false,
            drawBottomBorder = false,
            isGreen = false,
            onCellClicked = {},
            onCellDoubleClicked = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}
