package com.mathislaurent.domain.model

import java.util.Date

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val color: Long,
    val lastUpdateDate: Date?
)