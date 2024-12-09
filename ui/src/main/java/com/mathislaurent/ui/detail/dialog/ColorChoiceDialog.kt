package com.mathislaurent.ui.detail.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mathislaurent.ui.theme.DefaultCardColor
import com.mathislaurent.ui.theme.SimpleNotesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorChoiceDialog(
    color: Long,
    onColorChosen: (Long) -> Unit
) {
    val colorState = remember {
        mutableStateOf(color)
    }

    BasicAlertDialog(
        onDismissRequest = {
            onColorChosen(colorState.value)
        },
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DefaultCardColor.entries.forEach {
                    ColorBullet(
                        modifier = Modifier.padding(8.dp),
                        selected = colorState.value == it.color,
                        color = it.color,
                        onClick = {
                            colorState.value = it.color
                            onColorChosen(colorState.value)
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun ColorBullet(
    modifier: Modifier = Modifier,
    selected: Boolean,
    color: Long,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(Color(color), CircleShape)
            .let {
                if (selected)
                    it.border(
                        width = 4.dp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        shape = CircleShape
                    )
                else
                    it
            }
            .clickable { onClick() }
    )
}

@Preview
@Composable
private fun Preview() {
    SimpleNotesTheme {
        ColorChoiceDialog(
            color = DefaultCardColor.RED.color,
            onColorChosen = {}
        )
    }
}