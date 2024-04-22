package serverScreen.domain.usecase

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import callScreen.presentation.components.contactTable.ContactTableFilterStore
import core.presentation.utils.Sex
import org.junit.jupiter.api.Test

import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject
import org.koin.test.junit5.AutoCloseKoinTest
import serverScreen.domain.models.TasksTableItemData
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable.TasksTableFilterStore
import kotlin.test.assertContentEquals

class GetFilteredTaskListUseCaseTest {

    private val useCase by inject<GetFilteredTaskListUseCase>(GetFilteredTaskListUseCase::class.java)

    @Test
    fun `return filtered task list`() {

        startKoin {
            modules(
                module {
                    single { GetFilteredTaskListUseCase() }
                }
            )
        }

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

        val filterStore = TasksTableFilterStore(
            surnameFilterText = mutableStateOf(""),
            nameFilterText = mutableStateOf(""),
            patronymicFilterText = mutableStateOf(""),
            phoneNumberFilterText = mutableStateOf(""),
            messageTemplateFilterText = mutableStateOf("баню")
        )

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