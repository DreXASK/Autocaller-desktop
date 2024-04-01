package serverScreen.domain

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import callScreen.domain.models.ContactTableItemData
import org.koin.java.KoinJavaComponent.inject
import serverScreen.domain.models.TasksTableItemData
import serverScreen.domain.usecase.AddTaskToTableUseCase
import serverScreen.domain.usecase.GetFilteredTasksListUseCase
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable.TasksTableFilterStore

class TasksTable {
    private val getFilteredTasksListUseCase by inject<GetFilteredTasksListUseCase>(
        GetFilteredTasksListUseCase::class.java
    )
    private val addTaskToTableUseCase by inject<AddTaskToTableUseCase>(AddTaskToTableUseCase::class.java)

    val filterStore by inject<TasksTableFilterStore>(TasksTableFilterStore::class.java)

    private val tasksList: SnapshotStateList<TasksTableItemData> = mutableStateListOf()
    val tasksListFiltered: SnapshotStateList<TasksTableItemData> = mutableStateListOf()

    init {
        repeat(10) {
            addTasksToTable(
                listOf(
                    TasksTableItemData(
                        "Крючков",
                        "Илья",
                        "Николаевич",
                        "89020285716",
                        "Мессаге темплате"
                    ),
                    TasksTableItemData(
                        "Крючков",
                        "Илья",
                        "Николаевич",
                        "89020285716",
                        "Мессаге темплате 2"
                    ),
                    TasksTableItemData(
                        "Крючков",
                        "Илья",
                        "Николаевич",
                        "89020285716",
                        "Мессаге темплате 3"
                    ),
                )
            )
        }
        updateTasksListFiltered()
    }

    fun addTasksToTable(taskList: List<TasksTableItemData>) {
        taskList.forEach {
            addTaskToTableUseCase.execute(tasksList, it)
        }
        updateTasksListFiltered()
    }

    fun updateTasksListFiltered() {
        tasksListFiltered.clear()
        tasksListFiltered.addAll(
            getFilteredTasksListUseCase.execute(
                tasksList,
                filterStore
            )
        )
    }
}