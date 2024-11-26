package com.mathislaurent.data.di

import android.content.Context
import androidx.room.Room
import com.mathislaurent.data.database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DB_NAME = "note_db"

    @Singleton
    @Provides
    fun provideNoteDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        DB_NAME
    ).build()

    @Singleton
    @Provides
    fun provideNoteDao(db: NoteDatabase) = db.noteDao()
}
