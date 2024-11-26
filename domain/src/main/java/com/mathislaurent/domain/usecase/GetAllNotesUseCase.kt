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

class GetAllNotesUseCase @Inject constructor(
    private val repository: NoteRepository,
    private val noteMapper: NoteMapper,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(): Flow<List<Note>> {
        return repository.getAllNotes()
            .map { it.map { noteMapper(it) } }
            .distinctUntilChanged()
            .flowOn(dispatcher)
    }
}