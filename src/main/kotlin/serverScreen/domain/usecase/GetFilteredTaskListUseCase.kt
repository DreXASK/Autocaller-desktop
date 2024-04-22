package serverScreen.domain.usecase

import androidx.compose.runtime.snapshots.SnapshotStateList
import serverScreen.domain.models.TasksTableItemData
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable.TasksTableFilterStore

class GetFilteredTaskListUseCase {

    fun execute(
        tasksList: SnapshotStateList<TasksTableItemData>,
        filterStore: TasksTableFilterStore
    ): List<TasksTableItemData> {

        val tasksListFiltered = mutableListOf<TasksTableItemData>()

        tasksListFiltered.addAll(
            tasksList.filter { task ->
                isFieldContainsFilterText(task.surname, filterStore.surnameFilterText.value)
                        && isFieldContainsFilterText(task.name, filterStore.nameFilterText.value)
                        && isFieldContainsFilterText(task.patronymic, filterStore.patronymicFilterText.value)
                        && isFieldContainsFilterText(task.phoneNumber, filterStore.phoneNumberFilterText.value)
                        && isFieldContainsFilterText(task.messageTemplate, filterStore.messageTemplateFilterText.value)
            }
        )

        return tasksListFiltered
    }

    private fun isFieldContainsFilterText(
        fieldText: String?,
        filterText: String
    ): Boolean {
        if (filterText.isBlank())
            return true

        return fieldText?.contains(filterText, ignoreCase = true) ?: false
    }

}
