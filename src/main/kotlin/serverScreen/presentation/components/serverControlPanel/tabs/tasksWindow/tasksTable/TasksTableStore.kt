package serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import serverScreen.domain.models.TasksTableItemData

data class TasksTableStore(
    val tasksList: SnapshotStateList<TasksTableItemData> = mutableStateListOf(),
    val tasksListFiltered: SnapshotStateList<TasksTableItemData> = mutableStateListOf()
)