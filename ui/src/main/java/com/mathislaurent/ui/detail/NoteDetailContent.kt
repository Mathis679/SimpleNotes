package com.mathislaurent.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mathislaurent.domain.model.Note
import com.mathislaurent.ui.theme.SimpleNotesTheme
import com.mathislaurent.ui.theme.errorLight
import com.mathislaurent.ui.theme.primaryContainerLight

@Composable
fun NoteDetailContent(
    state: NoteDetailViewModel.DetailNoteUiState,
    onSave: (title: String, content: String, color: Long) -> Unit,
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
                primaryContainerLight.value.toLong()
            }
        )
    }

    Scaffold(
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
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = title.value,
                        onValueChange = { newValue ->
                            title.value = newValue
                        },
                        maxLines = 2,
                        textStyle = MaterialTheme.typography.titleLarge
                    )
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState()),
                        value = content.value,
                        onValueChange = { newValue ->
                            content.value = newValue
                        }
                    )
                }
            }
        }
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
                    title = "Note simple",
                    content = "Contenu de la note blablabla",
                    color = errorLight.value.toLong(),
                    lastUpdateDate = null
                )
            ),
            onSave = { title, content, color ->  }
        )
    }
}