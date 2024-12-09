package com.mathislaurent.ui.theme.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mathislaurent.ui.theme.SimpleNotesTheme
import com.mathislaurent.ui.theme.primaryLight

@Composable
fun NoteCard(
    onClick: () -> Unit,
    title: String,
    color: Color
) {
    Card(
        modifier = Modifier
            .aspectRatio(1f),
        onClick = { onClick() },
        colors = CardDefaults.cardColors().copy(containerColor = color)
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            maxLines = 2
        )
    }
}

@Preview
@Composable
private fun Preview() {
    SimpleNotesTheme {
        NoteCard(
            onClick = {},
            title = "Note avec un long, très long, très très long titre",
            color = primaryLight
        )
    }
}