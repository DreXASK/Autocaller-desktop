package serverScreen.domain.usecase

import callScreen.domain.models.ContactTableItemData
import serverScreen.domain.models.TasksTableItemData

class AddTaskToTableUseCase {
    fun execute(
        tasksTableItemDataList : MutableList<TasksTableItemData>,
        itemData: TasksTableItemData
    ) {
        tasksTableItemDataList.add(itemData)
    }
}