package com.mathislaurent.ui.list.item

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.mathislaurent.domain.model.Note

@Composable
fun NoteItem(
    note: Note,
    onClick: () -> Unit
) {
    Card(onClick = { onClick() }) {
        Text(text = note.title)
    }
}