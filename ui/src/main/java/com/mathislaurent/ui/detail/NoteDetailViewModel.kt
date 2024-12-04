package com.mathislaurent.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mathislaurent.domain.model.Note
import com.mathislaurent.domain.usecase.CreateNoteUseCase
import com.mathislaurent.domain.usecase.GetNoteUseCase
import com.mathislaurent.domain.usecase.UpdateNoteUseCase
import com.mathislaurent.ui.navigation.NOTE_DETAIL_ID_PARAM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    getNoteUseCase: GetNoteUseCase,
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val noteId: Int? = savedStateHandle[NOTE_DETAIL_ID_PARAM]

    val uiState: StateFlow<DetailNoteUiState> =
        if (noteId != null && noteId > -1) {
            getNoteUseCase(noteId).map {
                if (it != null) {
                    DetailNoteUiState.Update(it)
                } else {
                    DetailNoteUiState.NotFound
                }
            }
        } else {
            flow {
                emit(DetailNoteUiState.New)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DetailNoteUiState.Loading
        )

    private val _returnToList = MutableStateFlow<Boolean>(false)
    val returnToList: StateFlow<Boolean> = _returnToList

    sealed class DetailNoteUiState {
        data object Loading: DetailNoteUiState()
        data object New: DetailNoteUiState()
        data class Update(
            val note: Note
        ): DetailNoteUiState()
        data object NotFound: DetailNoteUiState()
    }

    fun saveNewNote(title: String, content: String, color: Long) {
        viewModelScope.launch {
            createNoteUseCase(
                Note(
                    id = 0,
                    title = title,
                    content = content,
                    color = color,
                    lastUpdateDate = Date()
                )
            )
            _returnToList.update {
                true
            }
        }
    }

    fun editNote(note: Note, title: String, content: String, color: Long) {
        viewModelScope.launch {
            updateNoteUseCase(
                note.copy(
                    title = title,
                    content = content,
                    color = color,
                    lastUpdateDate = Date()
                )
            )
            _returnToList.update {
                true
            }
        }
    }
}