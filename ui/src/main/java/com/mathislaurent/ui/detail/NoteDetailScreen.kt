package com.mathislaurent.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun NoteDetailScreen(
    viewModel: NoteDetailViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    Box(modifier = Modifier.fillMaxSize()) {
        when(state) {
            NoteDetailViewModel.DetailNoteUiState.New -> {
                Button(onClick = { viewModel.saveNewNote() }) {
                    Text("Save New")
                }
            }
            is NoteDetailViewModel.DetailNoteUiState.Update -> {
                Button(onClick = { viewModel.editNote(note = state.note) }) {
                    Text("Save")
                }
            }
            NoteDetailViewModel.DetailNoteUiState.NotFound -> {
                Text("Note not found")
            }
        }
    }

}