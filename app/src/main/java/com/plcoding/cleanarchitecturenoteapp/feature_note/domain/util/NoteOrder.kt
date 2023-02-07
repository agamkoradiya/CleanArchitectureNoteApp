package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util

import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note

/**
 * CREATED BY Aagam Koradiya on 10-06-2022
 */

sealed class NoteOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : NoteOrder(orderType)
    class Date(orderType: OrderType) : NoteOrder(orderType)
    class Color(orderType: OrderType) : NoteOrder(orderType)

    fun copy(orderType: OrderType): NoteOrder {
        return when (this) {
            is Color -> Color(orderType)
            is Date -> Date(orderType)
            is Title -> Title(orderType)
        }
    }
}