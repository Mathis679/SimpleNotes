package com.mathislaurent.domain.usecase

import com.mathislaurent.core.di.DefaultDispatcher
import com.mathislaurent.data.repository.NoteRepository
import com.mathislaurent.domain.mapper.NoteMapper
import com.mathislaurent.domain.model.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(
    private val repository: NoteRepository,
    private val noteMapper: NoteMapper,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(id: Int): Flow<Note?> {
        return repository.getNote(id)
            .map { it?.let { noteMapper(it) } }
            .distinctUntilChanged()
            .flowOn(dispatcher)
    }
}