package serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.inject
import serverScreen.domain.models.TasksTableItemData
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable.TasksTableFilterStore
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable.TasksTableStore
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable.TasksTableUI

@Preview
@Composable
fun TasksWindow() {
    val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

    TasksTableUI(
        viewModel.tasksTable.tasksTableStore,
        viewModel.tasksTable.filterStore,
        viewModel.tasksTable::updateTasksListFiltered
    )
}