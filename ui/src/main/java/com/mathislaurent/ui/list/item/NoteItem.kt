package com.mathislaurent.ui.list.item

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.mathislaurent.domain.model.Note
import com.mathislaurent.ui.theme.component.NoteCard

@Composable
fun NoteItem(
    note: Note,
    onClick: () -> Unit
) {
    NoteCard(
        title = note.title,
        color = Color(note.color),
        onClick = onClick
    )
}