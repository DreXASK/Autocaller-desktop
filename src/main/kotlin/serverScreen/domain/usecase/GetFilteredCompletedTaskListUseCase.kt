package serverScreen.domain.usecase

import androidx.compose.runtime.snapshots.SnapshotStateList
import serverScreen.domain.models.CompletedTaskData
import serverScreen.presentation.components.serverControlPanel.tabs.completedTasksWindow.completedTasksTable.CompletedTasksTableFilterStore

class GetFilteredCompletedTaskListUseCase {

    fun execute(
        tasksList: SnapshotStateList<CompletedTaskData>,
        filterStore: CompletedTasksTableFilterStore
    ): List<CompletedTaskData> {

        val completedTasksListFiltered = mutableListOf<CompletedTaskData>()

        completedTasksListFiltered.addAll(
            tasksList.filter { task ->
                isFieldContainsFilterText(task.surname, filterStore.surnameFilterText.value)
                        && isFieldContainsFilterText(task.name, filterStore.nameFilterText.value)
                        && isFieldContainsFilterText(task.patronymic, filterStore.patronymicFilterText.value)
                        && isFieldContainsFilterText(task.phoneNumber, filterStore.phoneNumberFilterText.value)
                        && isFieldContainsFilterText(task.messageText, filterStore.messageTemplateFilterText.value)
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
