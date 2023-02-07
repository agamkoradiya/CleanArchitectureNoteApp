package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_cases

import com.google.common.truth.Truth.assertThat
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.FakeNoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.InvalidNoteException
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

/**
 * CREATED BY Aagam Koradiya on 11-06-2022
 */

class InsertNoteUseCaseTest {

    lateinit var fakeNoteRepository: FakeNoteRepository
    lateinit var insertNoteUseCase: InsertNoteUseCase

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        insertNoteUseCase = InsertNoteUseCase(fakeNoteRepository)
    }

    @Test
    fun `Test insert note with blank title, throws exception`() = runBlocking {
        try {
            insertNoteUseCase(
                Note(
                    title = "",
                    content = "Dummy Content",
                    timestamp = 12345L,
                    color = 123
                )
            )
            fail("method should throw")
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(InvalidNoteException::class.java)
            assertThat(e).hasMessageThat().contains("Title can't be empty")
        }

    }

    @Test
    fun `Test insert note with blank content, throws exception`() = runBlocking {
        try {
            insertNoteUseCase(
                Note(
                    title = "Dummy title",
                    content = "",
                    timestamp = 12345L,
                    color = 123
                )
            )
            fail("method should throw");
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(InvalidNoteException::class.java)
            assertThat(e).hasMessageThat().contains("Content can't be empty")
        }

    }

    @Test
    fun `Test insert valid note, check inserted or not`() = runBlocking {
        try {
            insertNoteUseCase(
                Note(
                    title = "Dummy title",
                    content = "Dummy content",
                    timestamp = 12345L,
                    color = 123
                )
            )
        } catch (e: Exception) {
            fail("method should not throw");
        }

        val notes = fakeNoteRepository.getNotes().first()
        assertThat(notes).contains(Note(
            title = "Dummy title",
            content = "Dummy content",
            timestamp = 12345L,
            color = 123
        ))

    }
}