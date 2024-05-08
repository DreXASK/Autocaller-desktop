package serverScreen.domain

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import serverScreen.domain.models.CompletedTaskData
import serverScreen.domain.usecase.GetFilteredCompletedTaskListUseCase
import serverScreen.presentation.components.serverControlPanel.tabs.completedTasksWindow.completedTasksTable.CompletedTasksTableFilterStore

class CompletedTasksTable(
    private val getFilteredTaskListUseCase: GetFilteredCompletedTaskListUseCase,
    val filterStore: CompletedTasksTableFilterStore
) {

    private val completedTasksList: SnapshotStateList<CompletedTaskData> = mutableStateListOf()
    val completedTasksListFiltered: SnapshotStateList<CompletedTaskData> = mutableStateListOf()

    fun addTaskListToTable(taskList: List<CompletedTaskData>) {
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

    fun clearCallTaskList() {
        completedTasksList.clear()
        completedTasksListFiltered.clear()
    }
}
