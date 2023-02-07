package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.plcoding.cleanarchitecturenoteapp.ui.theme.*

/**
 * CREATED BY Aagam Koradiya on 10-06-2022
 */

@Entity
data class Note(
    val title: String,
    val content: String,
    val color: Int,
    val timestamp: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String) : Exception(message)