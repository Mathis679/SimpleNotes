package com.mathislaurent.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mathislaurent.ui.detail.NoteDetailScreen
import com.mathislaurent.ui.list.NoteListScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.NoteListScreenNavItem.route
    ) {
        composable(
            route = Screens.NoteListScreenNavItem.route
        ) {
            NoteListScreen(
                goToNote = { note ->
                    navController.navigate(Screens.NoteDetailScreenNavItem.buildRoute(note?.id))
                }
            )
        }
        composable(
            route = Screens.NoteDetailScreenNavItem.route,
            arguments = listOf(navArgument(NOTE_DETAIL_ID_PARAM) {
                type = NavType.IntType
            })
        ) {
            NoteDetailScreen()
        }
    }
}