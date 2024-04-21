package serverScreen.presentation.components.serverControlPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.tabs.ConnectionInfoWindow
import serverScreen.presentation.components.serverControlPanel.tabs.MainTabsWindow
import serverScreen.presentation.components.serverControlPanel.tabs.messageTemplatesWindow.MessageTemplatesWindow
import serverScreen.presentation.components.serverControlPanel.tabs.statisticsWindow.StatisticsWindow
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.TasksWindow

@Preview
@Composable
fun ServerControlPanelScreen() {
	val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

	when(viewModel.serverControlPanel.windowToDisplay.value) {
		ServerControlPanelWindows.Tabs -> MainTabsWindow()
		ServerControlPanelWindows.ConnectionInfo -> ConnectionInfoWindow()
		ServerControlPanelWindows.Tasks -> TasksWindow()
		ServerControlPanelWindows.DoneTasks -> TODO()
		ServerControlPanelWindows.MessageTemplates -> MessageTemplatesWindow()
		ServerControlPanelWindows.Statistics -> StatisticsWindow()
		ServerControlPanelWindows.CallProcessSettings -> TODO()
	}
}