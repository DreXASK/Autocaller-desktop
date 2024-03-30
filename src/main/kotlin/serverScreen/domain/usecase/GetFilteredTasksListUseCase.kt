package serverScreen.domain.usecase

import androidx.compose.runtime.snapshots.SnapshotStateList
import serverScreen.domain.models.TasksTableItemData
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable.TasksTableFilterStore

class GetFilteredTasksListUseCase {
    fun execute(
        tasksList: SnapshotStateList<TasksTableItemData>,
        filterStore: TasksTableFilterStore
    ): List<TasksTableItemData> {

        val tasksListFiltered = mutableListOf<TasksTableItemData>()

        tasksListFiltered.addAll(tasksList.filter {
            it.surname.contains(filterStore.surnameFilterText.value, ignoreCase = true)
                    && it.name.contains(filterStore.nameFilterText.value, ignoreCase = true)
                    && it.patronymic.contains(filterStore.patronymicFilterText.value, ignoreCase = true)
                    && it.phoneNumber.contains(filterStore.numberFilterText.value, ignoreCase = true)
                    && it.messageTemplate.contains(filterStore.messageTemplateFilterText.value, ignoreCase = true)
        })

        return tasksListFiltered
    }
}
