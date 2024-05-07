package serverScreen.domain

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import core.domain.models.CallTaskData
import serverScreen.domain.usecase.GetFilteredTaskListUseCase
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable.TasksTableFilterStore
import java.time.LocalDateTime

class TasksTable(
    private val getFilteredTaskListUseCase: GetFilteredTaskListUseCase,
    val filterStore: TasksTableFilterStore
) {

    private val tasksList: SnapshotStateList<CallTaskData> = mutableStateListOf()
    val tasksListFiltered: SnapshotStateList<CallTaskData> = mutableStateListOf()

    init {
        repeat(10) {
            addTaskListToTable(
                listOf(
                    CallTaskData(
                        "Крючков",
                        "Илья",
                        "Николаевич",
                        "89020285716",
                        "Мессаге темплате",
                        callAttempts = 0,
                        nextCallDateAndTimeUTC = LocalDateTime.now().toString()
                    ),
                    CallTaskData(
                        "Крючков",
                        "Илья",
                        "Николаевич",
                        "89020285716",
                        "Мессаге темплате 2",
                        callAttempts = 0,
                        nextCallDateAndTimeUTC = LocalDateTime.now().toString()
                    ),
                    CallTaskData(
                        "Крючков",
                        "Илья",
                        "Николаевич",
                        "89020285716",
                        "Мессаге темплате 3",
                        callAttempts = 0,
                        nextCallDateAndTimeUTC = LocalDateTime.now().toString()
                    ),
                )
            )
        }
        updateTasksListFiltered()
    }

    fun addTaskListToTable(taskList: List<CallTaskData>) {
        tasksList.addAll(taskList)
        updateTasksListFiltered()
    }

    fun updateTasksListFiltered() {
        tasksListFiltered.clear()
        tasksListFiltered.addAll(
            getFilteredTaskListUseCase.execute(
                tasksList,
                filterStore
            )
        )
    }
}