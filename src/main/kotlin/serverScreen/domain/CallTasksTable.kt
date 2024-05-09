package serverScreen.domain

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import core.domain.models.CallTaskData
import serverScreen.domain.usecase.GetFilteredTaskListUseCase
import serverScreen.presentation.components.serverControlPanel.tabs.callTasksWindow.callTasksTable.CallTasksTableFilterStore

class CallTasksTable(
    private val getFilteredTaskListUseCase: GetFilteredTaskListUseCase,
    val filterStore: CallTasksTableFilterStore
) {

    private val callTasksList: SnapshotStateList<CallTaskData> = mutableStateListOf()
    val callTasksListFiltered: SnapshotStateList<CallTaskData> = mutableStateListOf()

    fun addTaskListToTable(taskList: List<CallTaskData>) {
        callTasksList.addAll(taskList)
    }

    fun updateTasksListFiltered() {
        callTasksListFiltered.clear()
        callTasksListFiltered.addAll(
            getFilteredTaskListUseCase.execute(
                callTasksList,
                filterStore
            )
        )
    }

    fun clearCallTaskList() {
        callTasksList.clear()
        callTasksListFiltered.clear()
    }

    fun removeTask(id: Long) {
        callTasksList.removeIf { it.id == id }
        updateTasksListFiltered()
    }
}