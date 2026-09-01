package com.idt.core.design_system

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun Modifier.optional(predicate: () -> Boolean, modifier: Modifier): Modifier =
    if (predicate()) this then modifier
    else this

fun Modifier.gridBorder(
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
