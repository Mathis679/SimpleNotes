package com.mathislaurent.domain.usecase

import com.mathislaurent.core.di.DefaultDispatcher
import com.mathislaurent.data.repository.NoteRepository
import com.mathislaurent.domain.mapper.NoteMapper
import com.mathislaurent.domain.model.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetLastNoteUseCase @Inject constructor(
    private val repository: NoteRepository,
    private val noteMapper: NoteMapper,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {
    operator suspend fun invoke(): Note? = withContext(dispatcher) {
        repository.getLastNote()?.let { noteMapper(it) }
    }
}