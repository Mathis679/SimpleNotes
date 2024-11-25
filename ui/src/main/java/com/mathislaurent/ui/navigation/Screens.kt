package com.mathislaurent.ui.navigation

const val NOTE_LIST_ROUTE = "list"

const val NOTE_DETAIL_ID_PARAM = "noteId"
const val NOTE_DETAIL_ROUTE = "detail/{$NOTE_DETAIL_ID_PARAM}"

sealed class Screens(val route: String) {
    data object NoteListScreenNavItem: Screens(NOTE_LIST_ROUTE)
    data object NoteDetailScreenNavItem: Screens(NOTE_DETAIL_ROUTE)
}