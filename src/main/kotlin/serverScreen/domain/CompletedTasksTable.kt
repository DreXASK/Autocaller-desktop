package serverScreen.domain

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import serverScreen.domain.models.CompletedTasksTableItemData
import serverScreen.domain.usecase.GetFilteredCompletedTaskListUseCase
import serverScreen.presentation.components.serverControlPanel.tabs.completedTasksWindow.completedTasksTable.CompletedTasksTableFilterStore

class CompletedTasksTable(
    private val getFilteredTaskListUseCase: GetFilteredCompletedTaskListUseCase,
    val filterStore: CompletedTasksTableFilterStore
) {

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
