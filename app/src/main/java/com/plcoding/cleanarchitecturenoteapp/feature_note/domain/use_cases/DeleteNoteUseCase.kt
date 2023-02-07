package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_cases

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository

/**
 * CREATED BY Aagam Koradiya on 10-06-2022
 */

class DeleteNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}