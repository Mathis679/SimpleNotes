package com.mathislaurent.ui.list.item

import androidx.compose.runtime.Composable
import com.mathislaurent.ui.theme.component.NewNoteCard

@Composable
fun NewItem(
    onClick: () -> Unit
) {
    NewNoteCard(onClick = onClick)
}