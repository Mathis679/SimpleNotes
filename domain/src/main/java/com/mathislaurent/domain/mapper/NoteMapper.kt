package com.mathislaurent.domain.mapper

import com.mathislaurent.core.di.DefaultDispatcher
import com.mathislaurent.data.model.NoteEntity
import com.mathislaurent.domain.model.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteMapper @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(noteEntity: NoteEntity): Note = withContext(dispatcher) {
        Note(
            id = noteEntity.id,
            title = noteEntity.title,
            content = noteEntity.content,
            color = noteEntity.color,
            lastUpdateDate = noteEntity.lastUpdateDate
        )
    }

    suspend operator fun invoke(note: Note): NoteEntity = withContext(dispatcher) {
        NoteEntity(
            id = note.id,
            title = note.title,
            content = note.content,
            color = note.color,
            lastUpdateDate = note.lastUpdateDate
        )
    }
}