package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.add_edit_note

/**
 * CREATED BY Aagam Koradiya on 10-06-2022
 */

data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)