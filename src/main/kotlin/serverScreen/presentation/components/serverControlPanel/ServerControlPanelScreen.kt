package serverScreen.presentation.components.serverControlPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.tabs.ConnectionInfoWindow
import serverScreen.presentation.components.serverControlPanel.tabs.MainTabsWindow
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.TasksWindow

@Preview
@Composable
fun ServerControlPanelScreen() {
	val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

	when(viewModel.serverControlPanelSettings.windowToDisplay.value) {
		ServerControlPanelWindows.TabsWindow -> MainTabsWindow()
		ServerControlPanelWindows.ConnectionInfoWindow -> ConnectionInfoWindow()
		ServerControlPanelWindows.Tasks -> TasksWindow()
		ServerControlPanelWindows.MessageTemplates -> TODO()
		ServerControlPanelWindows.Statistics -> TODO()
		ServerControlPanelWindows.ListOfConnected -> TODO()
		ServerControlPanelWindows.ListOfPendingConnection -> TODO()
	}
}