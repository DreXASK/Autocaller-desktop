package unit.serverScreen.domain.usecase

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import org.junit.Rule
import org.junit.jupiter.api.Test

import serverScreen.domain.models.TasksTableItemData
import serverScreen.domain.usecase.GetFilteredTaskListUseCase
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable.TasksTableFilterStore
import kotlin.test.assertContentEquals

class GetFilteredTaskListUseCaseTest {

    private val useCase = GetFilteredTaskListUseCase()

    @get:Rule
    val initialTaskList = mutableStateListOf(
        TasksTableItemData(
            null,
            "John",
            null,
            "88005553535",
            "Спишь? Это Дима. В баню завтра идешь?"
        ),
        TasksTableItemData(
            "Иваненко",
            "Иван",
            "Иванович",
            "88005553536",
            "Ну как там с деньгами?"
        )
    )

    @get:Rule
    val filterStore = TasksTableFilterStore(
        surnameFilterText = mutableStateOf(""),
        nameFilterText = mutableStateOf(""),
        patronymicFilterText = mutableStateOf(""),
        phoneNumberFilterText = mutableStateOf(""),
        messageTemplateFilterText = mutableStateOf("баню")
    )


    @Test
    fun `return filtered task list`() {
        val actual = useCase.execute(initialTaskList, filterStore)

        val expected = listOf(
            TasksTableItemData(
                null,
                "John",
                null,
                "88005553535",
                "Спишь? Это Дима. В баню завтра идешь?"
            )
        )

        assertContentEquals(expected = expected, actual = actual)
    }
}