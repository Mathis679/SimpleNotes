package com.mathislaurent.ui.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

    if (state != NoteDetailViewModel.DetailNoteUiState.Loading) {
        NoteDetailContent(
            state = state,
            onSave = { title, content, color ->
                when(state) {
                    NoteDetailViewModel.DetailNoteUiState.New -> {
                        viewModel.saveNewNote(
                            title = title,
                            content = content,
                            color = color
                        )
                    }
                    is NoteDetailViewModel.DetailNoteUiState.Update -> {
                        viewModel.editNote(
                            note = state.note,
                            title = title,
                            content = content,
                            color = color
                        )
                    }
                    else -> {
                        // do nothing
                    }
                }
            }
        )
    }
}