package serverScreen.domain

import org.koin.java.KoinJavaComponent.inject
import serverScreen.domain.usecase.GetFilteredTasksListUseCase
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable.TasksTableFilterStore
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable.TasksTableStore

class TasksTable {
    private val getFilteredTasksListUseCase by inject<GetFilteredTasksListUseCase>(
        GetFilteredTasksListUseCase::class.java
    )

    val tasksTableStore by inject<TasksTableStore>(TasksTableStore::class.java)
    val filterStore by inject<TasksTableFilterStore>(TasksTableFilterStore::class.java)

    fun updateTasksListFiltered() {
        tasksTableStore.tasksListFiltered.clear()
        tasksTableStore.tasksListFiltered.addAll(
            getFilteredTasksListUseCase.execute(
                tasksTableStore.tasksList,
                filterStore
            )
        )
    }
}