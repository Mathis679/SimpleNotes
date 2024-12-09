package com.mathislaurent.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mathislaurent.domain.model.Note
import com.mathislaurent.ui.list.item.NewItem
import com.mathislaurent.ui.list.item.NoteItem

@Composable
fun NoteListScreen(
    viewModel: NoteListViewModel = hiltViewModel(),
    goToNote: (Note?) -> Unit
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    NoteListContent(
        state = state,
        goToNote = goToNote
    )
}

@Composable
fun NoteListContent(
    state: NoteListViewModel.NoteListUiState,
    goToNote: (Note?) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .statusBarsPadding()
    ) {
        LazyVerticalGrid(
            modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer),
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            columns = GridCells.Fixed(2)
        ) {
            if (state is NoteListViewModel.NoteListUiState.Success) {
                items(state.noteList) { note ->
                    NoteItem(
                        note = note,
                        onClick = {
                            goToNote(note)
                        }
                    )
                }
            }
            item {
                NewItem(
                    onClick = {
                        goToNote(null)
                    }
                )
            }
        }
    }
}