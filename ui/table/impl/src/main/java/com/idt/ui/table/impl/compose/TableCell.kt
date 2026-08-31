package com.idt.ui.table.impl.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.idt.core.design_system.theme.EditabletableTheme

@Composable
internal fun TableCell(
    text: String,
    drawEndBorder: Boolean,
    drawBottomBorder: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.gridBorder(
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

private fun Modifier.gridBorder(
    drawEndBorder: Boolean,
    drawBottomBorder: Boolean,
): Modifier = drawBehind {
    val strokeWidth = 1.dp.toPx()
    val inset = strokeWidth / 2f

    drawLine(
        color = Color.Black,
        start = Offset(0f, inset),
        end = Offset(size.width, inset),
        strokeWidth = strokeWidth,
    )
    drawLine(
        color = Color.Black,
        start = Offset(inset, 0f),
        end = Offset(inset, size.height),
        strokeWidth = strokeWidth,
    )
    if (drawEndBorder) {
        drawLine(
            color = Color.Black,
            start = Offset(size.width - inset, 0f),
            end = Offset(size.width - inset, size.height),
            strokeWidth = strokeWidth,
        )
    }
    if (drawBottomBorder) {
        drawLine(
            color = Color.Black,
            start = Offset(0f, size.height - inset),
            end = Offset(size.width, size.height - inset),
            strokeWidth = strokeWidth,
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
            modifier = Modifier.padding(16.dp),
        )
    }
}
