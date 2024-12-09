package com.mathislaurent.ui.theme.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mathislaurent.ui.theme.SimpleNotesTheme

@Composable
fun NewNoteCard(
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .aspectRatio(1f),
        onClick = { onClick() },
        colors = CardDefaults.cardColors().copy(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .dashedBorder(
                    color = MaterialTheme.colorScheme.scrim,
                    shape = CardDefaults.shape,
                    strokeWidth = 8.dp,
                    dashLength = 16.dp,
                    gapLength = 16.dp
                )
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(56.dp),
                imageVector = Icons.Filled.Add,
                contentDescription = "Add icon",
                tint = MaterialTheme.colorScheme.scrim
            )
        }
    }
}

fun Modifier.dashedBorder(
    color: Color,
    shape: Shape,
    strokeWidth: Dp = 2.dp,
    dashLength: Dp = 4.dp,
    gapLength: Dp = 4.dp,
    cap: StrokeCap = StrokeCap.Round
) = dashedBorder(brush = SolidColor(color), shape, strokeWidth, dashLength, gapLength, cap)

fun Modifier.dashedBorder(
    brush: Brush,
    shape: Shape,
    strokeWidth: Dp = 2.dp,
    dashLength: Dp = 4.dp,
    gapLength: Dp = 4.dp,
    cap: StrokeCap = StrokeCap.Round
) = drawWithContent {

    val outline = shape.createOutline(size, layoutDirection, density = this)

    val dashedStroke = Stroke(
        cap = cap,
        width = strokeWidth.toPx(),
        pathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(dashLength.toPx(), gapLength.toPx())
        )
    )

    drawContent()

    drawOutline(
        outline = outline,
        style = dashedStroke,
        brush = brush
    )
}

@Preview
@Composable
private fun Preview() {
    SimpleNotesTheme {
        NewNoteCard(
            onClick = {}
        )
    }
}