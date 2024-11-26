package com.mathislaurent.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mathislaurent.data.helper.Converters
import com.mathislaurent.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}