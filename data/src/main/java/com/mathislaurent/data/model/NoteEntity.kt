package com.mathislaurent.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val title: String,
    val content: String,
    val color: Long,
    val lastUpdateDate: Date?
)