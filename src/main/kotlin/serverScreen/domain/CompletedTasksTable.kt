package serverScreen.domain

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import org.koin.java.KoinJavaComponent.inject
import serverScreen.domain.models.CompletedTasksTableItemData
import serverScreen.domain.usecase.GetFilteredCompletedTaskListUseCase
import serverScreen.presentation.components.serverControlPanel.tabs.completedTasksWindow.completedTasksTable.CompletedTasksTableFilterStore
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable.TasksTableFilterStore

class CompletedTasksTable() {

    private val getFilteredTaskListUseCase by inject<GetFilteredCompletedTaskListUseCase>(
        GetFilteredCompletedTaskListUseCase::class.java
    )

    val filterStore by inject<CompletedTasksTableFilterStore>(TasksTableFilterStore::class.java)

    private val completedTasksList: SnapshotStateList<CompletedTasksTableItemData> = mutableStateListOf()
    val completedTasksListFiltered: SnapshotStateList<CompletedTasksTableItemData> = mutableStateListOf()

    init {
        repeat(10) {
            addTaskListToTable(
                listOf(
                    CompletedTasksTableItemData(
                        "Крючков",
                        "Илья",
                        "Николаевич",
                        "89020285716",
                        "Мессаге темплате"
                    ),
                    CompletedTasksTableItemData(
                        "Крючков",
                        "Илья",
                        "Николаевич",
                        "89020285716",
                        "Мессаге темплате 2"
                    ),
                    CompletedTasksTableItemData(
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

    fun addTaskListToTable(taskList: List<CompletedTasksTableItemData>) {
        completedTasksList.addAll(taskList)
        updateTasksListFiltered()
    }

    fun updateTasksListFiltered() {
        completedTasksListFiltered.clear()
        completedTasksListFiltered.addAll(
            getFilteredTaskListUseCase.execute(
                completedTasksList,
                filterStore
            )
        )
    }
}
