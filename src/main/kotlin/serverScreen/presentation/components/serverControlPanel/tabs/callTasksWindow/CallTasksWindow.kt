package serverScreen.presentation.components.serverControlPanel.tabs.callTasksWindow

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.presentation.components.OutlinedButtonWithIconText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.ServerControlPanelWindows
import serverScreen.presentation.components.serverControlPanel.tabs.callTasksWindow.callTasksTable.CallTasksTableUI

@Preview
@Composable
fun TasksWindow() {
    val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

    LaunchedEffect(Unit) {
        loadDataFromServer(viewModel)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(start = 30.dp, top = 10.dp, end = 30.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Row {
                OutlinedButtonWithIconText(
                    modifier = Modifier.fillMaxHeight(),
                    icon = Icons.Rounded.ArrowBack,
                    text = "Назад в меню"
                ) {
                    viewModel.serverControlPanel.windowToDisplay.value =
                        ServerControlPanelWindows.TABS
                }
            }

            OutlinedButton(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        loadDataFromServer(viewModel)
                    }
                },
                modifier = Modifier.align(Alignment.TopEnd).fillMaxHeight()
            ) {
                Icon(Icons.Rounded.Refresh, "Refresh the table")
            }


        }

        CallTasksTableUI(
            tasksListFiltered = viewModel.callTasksTable.callTasksListFiltered,
            filterStore = viewModel.callTasksTable.filterStore,
            contentPadding = PaddingValues(start = 30.dp, top = 10.dp, end = 30.dp, bottom = 30.dp),
            onButtonClicked = { id ->
                if (viewModel.removeCallTaskFromServer(id)) {
                    viewModel.callTasksTable.removeTask(id)
                    true
                } else false
            },
            onFilterValueChanged = viewModel.callTasksTable::updateTasksListFiltered
        )
    }
}

private suspend fun loadDataFromServer(viewModel: ServerScreenViewModel) {
    viewModel.getCallTaskListFromServer()?.let {
        viewModel.callTasksTable.clearCallTaskList()
        viewModel.callTasksTable.addTaskListToTable(it)
        viewModel.callTasksTable.updateTasksListFiltered()
    }
}