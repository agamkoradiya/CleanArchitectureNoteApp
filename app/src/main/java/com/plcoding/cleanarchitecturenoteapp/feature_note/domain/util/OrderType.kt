package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util

/**
 * CREATED BY Aagam Koradiya on 10-06-2022
 */

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}