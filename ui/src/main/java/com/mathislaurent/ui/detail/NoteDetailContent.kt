package com.mathislaurent.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mathislaurent.domain.model.Note
import com.mathislaurent.ui.detail.dialog.ColorChoiceDialog
import com.mathislaurent.ui.theme.DEFAULT_CARD_BLUE
import com.mathislaurent.ui.theme.SimpleNotesTheme
import com.mathislaurent.ui.theme.component.DetailTextField

@Composable
fun NoteDetailContent(
    state: NoteDetailViewModel.DetailNoteUiState,
    onSave: (title: String, content: String, color: Long) -> Unit,
    onDelete: () -> Unit,
) {
    val title = rememberSaveable {
        mutableStateOf(
            if (state is NoteDetailViewModel.DetailNoteUiState.Update) {
                state.note.title
            } else {
                ""
            }
        )
    }

    val content = rememberSaveable {
        mutableStateOf(
            if (state is NoteDetailViewModel.DetailNoteUiState.Update) {
                state.note.content
            } else {
                ""
            }
        )
    }

    val color = rememberSaveable {
        mutableStateOf(
            if (state is NoteDetailViewModel.DetailNoteUiState.Update) {
                state.note.color
            } else {
                DEFAULT_CARD_BLUE
            }
        )
    }

    val showColorDialog = remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier.imePadding(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onSave(
                        title.value,
                        content.value,
                        color.value
                    )
                }
            ) {
                Icon(Icons.Filled.Done, "Floating action button.")
            }
        },
        containerColor = Color(color.value)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = Color(color.value))
        ) {
            when(state) {
                NoteDetailViewModel.DetailNoteUiState.NotFound -> {
                    Text("Note not found")
                }
                else -> {
                    Row {
                        DetailTextField(
                            modifier = Modifier.weight(4f),
                            value = title.value,
                            onValueChange = { newValue ->
                                title.value = newValue
                            },
                            maxLines = 2,
                            textStyle = MaterialTheme.typography.titleLarge,
                            placeholder = "Titre..."
                        )
                        IconButton(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                showColorDialog.value = true
                            }
                        ) {
                            Icon(Icons.Filled.Face, "Change color icon")
                        }
                        IconButton(
                            modifier = Modifier.weight(1f),
                            onClick = {
                                onDelete()
                            }
                        ) {
                            Icon(Icons.Filled.Delete, "Change color icon")
                        }
                    }

                    DetailTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = content.value,
                        onValueChange = { newValue ->
                            content.value = newValue
                        },
                        textStyle = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }

    if (showColorDialog.value) {
        ColorChoiceDialog(
            color = color.value,
            onColorChosen = { newColor ->
                color.value = newColor
                showColorDialog.value = false
            }
        )
    }
}


@Preview
@Composable
private fun Preview() {
    SimpleNotesTheme {
        NoteDetailContent(
            state = NoteDetailViewModel.DetailNoteUiState.Update(
                note = Note(
                    id = 0,
                    title = "",
                    content = "Contenu de la note blablabla",
                    color = DEFAULT_CARD_BLUE,
                    lastUpdateDate = null
                )
            ),
            onSave = { title, content, color ->  },
            onDelete = {}
        )
    }
}