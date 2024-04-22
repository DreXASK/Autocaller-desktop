package serverScreen.domain

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import org.koin.java.KoinJavaComponent.inject
import serverScreen.domain.models.TasksTableItemData
import serverScreen.domain.usecase.GetFilteredTasksListUseCase
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable.TasksTableFilterStore

class TasksTable {
    private val getFilteredTasksListUseCase by inject<GetFilteredTasksListUseCase>(
        GetFilteredTasksListUseCase::class.java
    )

    val filterStore by inject<TasksTableFilterStore>(TasksTableFilterStore::class.java)

    private val tasksList: SnapshotStateList<TasksTableItemData> = mutableStateListOf()
    val tasksListFiltered: SnapshotStateList<TasksTableItemData> = mutableStateListOf()

    init {
        repeat(10) {
            addTaskListToTable(
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

    fun addTaskListToTable(taskList: List<TasksTableItemData>) {
        tasksList.addAll(taskList)
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