package serverScreen.presentation.components.serverControlPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.tabs.connectionInfoWindow.ConnectionInfoWindow
import serverScreen.presentation.components.serverControlPanel.tabs.MainTabsWindow
import serverScreen.presentation.components.serverControlPanel.tabs.callProcessSettingsWindow.CallProcessSettingsWindow
import serverScreen.presentation.components.serverControlPanel.tabs.completedTasksWindow.CompletedTasksWindow
import serverScreen.presentation.components.serverControlPanel.tabs.messageTemplatesWindow.MessageTemplateWindow
import serverScreen.presentation.components.serverControlPanel.tabs.statisticsWindow.StatisticsWindow
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.TasksWindow

@Preview
@Composable
fun ServerControlPanelScreen() {
	val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

	when(viewModel.serverControlPanel.windowToDisplay.value) {
		ServerControlPanelWindows.TABS -> MainTabsWindow()
		ServerControlPanelWindows.CONNECTION_INFO -> ConnectionInfoWindow()
		ServerControlPanelWindows.TASKS -> TasksWindow()
		ServerControlPanelWindows.DONE_TASKS -> CompletedTasksWindow()
		ServerControlPanelWindows.MESSAGE_TEMPLATES -> MessageTemplateWindow()
		ServerControlPanelWindows.STATISTICS -> StatisticsWindow()
		ServerControlPanelWindows.CALL_PROCESS_SETTINGS -> CallProcessSettingsWindow()
	}
}