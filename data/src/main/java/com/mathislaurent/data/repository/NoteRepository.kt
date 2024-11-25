package com.mathislaurent.data.repository

import com.mathislaurent.core.di.IoDispatcher
import com.mathislaurent.data.database.NoteDao
import com.mathislaurent.data.model.NoteEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getAllNotes(): List<NoteEntity> = withContext(dispatcher) {
        noteDao.getAllNotes()
    }

    suspend fun insertNote(noteEntity: NoteEntity) = withContext(dispatcher) {
        noteDao.insertNote(noteEntity)
    }

    suspend fun updateNote(noteEntity: NoteEntity) = withContext(dispatcher) {
        noteDao.updateNote(noteEntity)
    }

    suspend fun deleteNote(noteEntity: NoteEntity) = withContext(dispatcher) {
        noteDao.deleteNote(noteEntity)
    }
}