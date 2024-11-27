package com.mathislaurent.ui.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mathislaurent.domain.model.Note

@Composable
fun NoteListScreen(
    viewModel: NoteListViewModel = hiltViewModel(),
    goToNote: (Note?) -> Unit
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    Surface(
        modifier = Modifier.fillMaxSize().padding(56.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            if (state is NoteListViewModel.NoteListUiState.Success) {
                items(state.noteList) { note ->
                    Card(onClick = { goToNote(note) }) {
                        Text(text = note.title)
                    }
                }
            }
            item {
                Card(onClick = { goToNote(null) }) {
                    Text(text = "New")
                }
            }
        }
    }
}