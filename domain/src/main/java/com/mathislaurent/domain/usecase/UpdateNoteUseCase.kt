package com.mathislaurent.domain.usecase

import com.mathislaurent.core.di.DefaultDispatcher
import com.mathislaurent.data.repository.NoteRepository
import com.mathislaurent.domain.mapper.NoteMapper
import com.mathislaurent.domain.model.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
    private val noteMapper: NoteMapper,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(note: Note) = withContext(dispatcher) {
        noteRepository.updateNote(noteMapper(note))
    }
}