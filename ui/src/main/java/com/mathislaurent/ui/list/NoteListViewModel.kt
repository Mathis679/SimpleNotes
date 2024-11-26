package com.mathislaurent.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mathislaurent.domain.model.Note
import com.mathislaurent.domain.usecase.GetAllNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    getAllNotesUseCase: GetAllNotesUseCase
): ViewModel() {

    val uiState: StateFlow<NoteListUiState> = getAllNotesUseCase().map {
        if (it.isEmpty()) {
            NoteListUiState.Empty
        } else {
            NoteListUiState.Success(
                noteList = it
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = NoteListUiState.Empty
    )

    sealed class NoteListUiState {
        data object Empty: NoteListUiState()
        data class Success(
            val noteList: List<Note>
        ): NoteListUiState()
    }

    fun onNoteClicked(note: Note) {

    }

    fun onNewNoteClicked() {

    }
}