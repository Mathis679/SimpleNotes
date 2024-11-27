package com.mathislaurent.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun NoteDetailScreen(
    viewModel: NoteDetailViewModel = hiltViewModel(),
    goBack: () -> Unit
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect("navigation") {
        viewModel.returnToList.onEach {
            if (it) {
                goBack()
            }
        }.launchIn(this)
    }

    Box(modifier = Modifier.fillMaxSize().padding(56.dp)) {
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