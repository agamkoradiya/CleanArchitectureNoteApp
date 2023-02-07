package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_cases

/**
 * CREATED BY Aagam Koradiya on 10-06-2022
 */
data class NoteUseCases(
    val getNotesUseCase: GetNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val insertNoteUseCase: InsertNoteUseCase,
    val getNoteUseCase: GetNoteUseCase
)
