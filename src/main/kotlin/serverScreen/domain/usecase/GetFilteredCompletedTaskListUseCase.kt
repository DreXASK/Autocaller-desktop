package serverScreen.domain.usecase

import androidx.compose.runtime.snapshots.SnapshotStateList
import serverScreen.domain.models.CompletedTasksTableItemData
import serverScreen.domain.models.TasksTableItemData
import serverScreen.presentation.components.serverControlPanel.tabs.completedTasksWindow.completedTasksTable.CompletedTasksTableFilterStore
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable.TasksTableFilterStore

class GetFilteredCompletedTaskListUseCase {

    fun execute(
        tasksList: SnapshotStateList<CompletedTasksTableItemData>,
        filterStore: CompletedTasksTableFilterStore
    ): List<CompletedTasksTableItemData> {

        val completedTasksListFiltered = mutableListOf<CompletedTasksTableItemData>()

        completedTasksListFiltered.addAll(
            tasksList.filter { task ->
                isFieldContainsFilterText(task.surname, filterStore.surnameFilterText.value)
                        && isFieldContainsFilterText(task.name, filterStore.nameFilterText.value)
                        && isFieldContainsFilterText(task.patronymic, filterStore.patronymicFilterText.value)
                        && isFieldContainsFilterText(task.phoneNumber, filterStore.phoneNumberFilterText.value)
                        && isFieldContainsFilterText(task.messageTemplate, filterStore.messageTemplateFilterText.value)
            }
        )

        return completedTasksListFiltered
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
