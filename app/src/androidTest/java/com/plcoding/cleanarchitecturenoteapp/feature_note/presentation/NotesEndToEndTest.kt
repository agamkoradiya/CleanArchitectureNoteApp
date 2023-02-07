package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.plcoding.cleanarchitecturenoteapp.core.util.TestTags
import com.plcoding.cleanarchitecturenoteapp.di.AppModule
import com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.notes.NotesScreen
import com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.util.Screen
import com.plcoding.cleanarchitecturenoteapp.ui.theme.CleanArchitectureNoteAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * CREATED BY Aagam Koradiya on 11-06-2022
 */

@HiltAndroidTest
@UninstallModules(AppModule::class)
class NotesEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 0)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            CleanArchitectureNoteAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.NotesScreen.route
                ) {
                    composable(route = Screen.NotesScreen.route) {
                        NotesScreen(navController = navController)
                    }
                    composable(
                        route = Screen.AddEditNoteScreen.route +
                                "?noteId={noteId}&noteColor={noteColor}",
                        arguments = listOf(
                            navArgument(
                                name = "noteId"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                            navArgument(
                                name = "noteColor"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                        )
                    ) {
                        val color = it.arguments?.getInt("noteColor") ?: -1
                        AddEditNoteScreen(
                            navController = navController,
                            noteColor = color
                        )
                    }
                }
            }
        }
    }

    @Test
    fun saveNewNote_editAfterwards() {
        composeRule.onNodeWithContentDescription("Add").performClick()

        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).performTextInput("Hi, This is my first title")
        composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).performTextInput("This is a content for my first note.")
        composeRule.onNodeWithContentDescription("Save").performClick()

        composeRule.onNodeWithText("Hi, This is my first title").assertIsDisplayed()
        composeRule.onNodeWithText("Hi, This is my first title").performClick()

        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).assertTextEquals("Hi, This is my first title")
        composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).assertTextEquals("This is a content for my first note.")

        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).performTextClearance()
        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).performTextInput("Hi, This is my second title")
        composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).performTextInput("This is a content for my second note.")
        composeRule.onNodeWithContentDescription("Save").performClick()

        composeRule.onNodeWithText("Hi, This is my second title").assertIsDisplayed()
    }

    @Test
    fun save3Notes_check_title_asc() {
        for (i in 0..2) {
            composeRule.onNodeWithContentDescription("Add").performClick()

            composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).performTextInput(i.toString())
            composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).performTextInput(i.toString())
            composeRule.onNodeWithContentDescription("Save").performClick()
        }

        composeRule.onNodeWithContentDescription("Sort").performClick()
        composeRule.onNodeWithContentDescription("Title").performClick()
        composeRule.onNodeWithContentDescription("Ascending").performClick()

        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[0].assertTextEquals("0")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[1].assertTextEquals("1")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[2].assertTextEquals("2")
    }

    @Test
    fun save3Notes_check_title_des() {
        for (i in 0..2) {
            composeRule.onNodeWithContentDescription("Add").performClick()

            composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).performTextInput(i.toString())
            composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).performTextInput(i.toString())
            composeRule.onNodeWithContentDescription("Save").performClick()
        }

        composeRule.onNodeWithContentDescription("Sort").performClick()
        composeRule.onNodeWithContentDescription("Title").performClick()
        composeRule.onNodeWithContentDescription("Descending").performClick()

        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[0].assertTextEquals("2")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[1].assertTextEquals("1")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[2].assertTextEquals("0")
    }

    @Test
    fun save3Notes_check_date_asc() {
        for (i in 0..2) {
            composeRule.onNodeWithContentDescription("Add").performClick()

            composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).performTextInput(i.toString())
            composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).performTextInput(i.toString())
            composeRule.onNodeWithContentDescription("Save").performClick()
        }

        composeRule.onNodeWithContentDescription("Sort").performClick()
        composeRule.onNodeWithContentDescription("Date").performClick()
        composeRule.onNodeWithContentDescription("Ascending").performClick()

        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[0].assertTextEquals("0")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[1].assertTextEquals("1")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[2].assertTextEquals("2")
    }

    @Test
    fun save3Notes_check_date_des() {
        for (i in 0..2) {
            composeRule.onNodeWithContentDescription("Add").performClick()

            composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).performTextInput(i.toString())
            composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).performTextInput(i.toString())
            composeRule.onNodeWithContentDescription("Save").performClick()
        }

        composeRule.onNodeWithContentDescription("Sort").performClick()
        composeRule.onNodeWithContentDescription("Date").performClick()
        composeRule.onNodeWithContentDescription("Descending").performClick()

        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[0].assertTextEquals("2")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[1].assertTextEquals("1")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[2].assertTextEquals("0")
    }

    @Test
    fun save3Notes_check_title_date_sort() {

        for (i in 0..2) {
            composeRule.onNodeWithContentDescription("Add").performClick()

            composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).performTextInput(i.toString())
            composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).performTextInput(i.toString())
            composeRule.onNodeWithContentDescription("Save").performClick()
        }

        composeRule.onNodeWithContentDescription("Sort").performClick()
        composeRule.onNodeWithContentDescription("Title").performClick()
        composeRule.onNodeWithContentDescription("Ascending").performClick()

        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[0].assertTextEquals("0")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[1].assertTextEquals("1")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[2].assertTextEquals("2")

        //

        composeRule.onNodeWithContentDescription("Title").performClick()
        composeRule.onNodeWithContentDescription("Descending").performClick()

        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[0].assertTextEquals("2")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[1].assertTextEquals("1")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[2].assertTextEquals("0")

        //

        composeRule.onNodeWithContentDescription("Date").performClick()
        composeRule.onNodeWithContentDescription("Ascending").performClick()

        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[0].assertTextEquals("0")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[1].assertTextEquals("1")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[2].assertTextEquals("2")

        //

        composeRule.onNodeWithContentDescription("Date").performClick()
        composeRule.onNodeWithContentDescription("Descending").performClick()

        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[0].assertTextEquals("2")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[1].assertTextEquals("1")
        composeRule.onAllNodesWithTag(TestTags.NOTE_ITEM)[2].assertTextEquals("0")
    }

    @Test
    fun test_delete_note() {

        for (i in 0..2) {
            composeRule.onNodeWithContentDescription("Add").performClick()

            composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).performTextInput(i.toString())
            composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).performTextInput(i.toString())
            composeRule.onNodeWithContentDescription("Save").performClick()
        }

        composeRule.onNodeWithContentDescription("Sort").performClick()
        composeRule.onNodeWithContentDescription("Title").performClick()
        composeRule.onNodeWithContentDescription("Ascending").performClick()

        composeRule.onAllNodesWithTag(TestTags.NOTE_DELETE)[0].performClick()
        composeRule.onNodeWithText("0").assertDoesNotExist()

        composeRule.onAllNodesWithTag(TestTags.NOTE_DELETE)[1].performClick()
        composeRule.onNodeWithText("2").assertDoesNotExist()
    }

    @Test
    fun test_undo_feature() {

        for (i in 0..2) {
            composeRule.onNodeWithContentDescription("Add").performClick()

            composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD).performTextInput(i.toString())
            composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD).performTextInput(i.toString())
            composeRule.onNodeWithContentDescription("Save").performClick()
        }

        composeRule.onNodeWithContentDescription("Sort").performClick()
        composeRule.onNodeWithContentDescription("Title").performClick()
        composeRule.onNodeWithContentDescription("Ascending").performClick()

        composeRule.onAllNodesWithTag(TestTags.NOTE_DELETE)[1].performClick()
        composeRule.onNodeWithText("1").assertDoesNotExist()
        composeRule.onNodeWithText("Undo").performClick()
        composeRule.onNodeWithText("1").assertIsDisplayed()
    }
}