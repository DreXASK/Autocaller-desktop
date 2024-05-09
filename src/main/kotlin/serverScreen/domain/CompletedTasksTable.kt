package serverScreen.domain

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import serverScreen.domain.models.CompletedTaskData
import serverScreen.domain.usecase.GetFilteredCompletedTaskListUseCase
import serverScreen.presentation.components.serverControlPanel.tabs.completedTasksWindow.completedTasksTable.CompletedTasksTableFilterStore

class CompletedTasksTable(
    private val getFilteredCompletedTaskListUseCase: GetFilteredCompletedTaskListUseCase,
    val filterStore: CompletedTasksTableFilterStore
) {

    private val completedTasksList: SnapshotStateList<CompletedTaskData> = mutableStateListOf()
    val completedTasksListFiltered: SnapshotStateList<CompletedTaskData> = mutableStateListOf()

    fun addTaskListToTable(taskList: List<CompletedTaskData>) {
        completedTasksList.addAll(taskList)
    }

    fun updateTasksListFiltered() {
        completedTasksListFiltered.clear()
        completedTasksListFiltered.addAll(
            getFilteredCompletedTaskListUseCase.execute(
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
