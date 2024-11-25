package com.mathislaurent.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val content: String,
    val color: Long,
    val lastUpdateDate: Date?
)