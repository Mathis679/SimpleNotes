package com.mathislaurent.data.repository

import com.mathislaurent.core.di.IoDispatcher
import com.mathislaurent.data.database.NoteDao
import com.mathislaurent.data.model.NoteEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    fun getAllNotes(): Flow<List<NoteEntity>> = flow<List<NoteEntity>> {
        noteDao.getAllNotes()
    }.flowOn(dispatcher)

    fun getNote(id: Int): Flow<NoteEntity?> = flow<NoteEntity?> {
        noteDao.getNote(id)
    }.flowOn(dispatcher)

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