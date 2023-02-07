package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.util

/**
 * CREATED BY Aagam Koradiya on 10-06-2022
 */

sealed class Screen(val route: String) {
    object NotesScreen : Screen("notes_screen")
    object AddEditNoteScreen : Screen("add_edit_note_screen")
}