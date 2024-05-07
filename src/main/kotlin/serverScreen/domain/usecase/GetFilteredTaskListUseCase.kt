package serverScreen.domain.usecase

import androidx.compose.runtime.snapshots.SnapshotStateList
import core.domain.models.CallTaskData
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable.TasksTableFilterStore

class GetFilteredTaskListUseCase {

    fun execute(
        tasksList: SnapshotStateList<CallTaskData>,
        filterStore: TasksTableFilterStore
    ): List<CallTaskData> {

        val tasksListFiltered = mutableListOf<CallTaskData>()

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
