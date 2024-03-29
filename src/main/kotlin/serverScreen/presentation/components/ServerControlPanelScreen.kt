package serverScreen.presentation.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.ConnectionInfoWindow
import serverScreen.presentation.components.serverControlPanel.ServerControlPanelWindows
import serverScreen.presentation.components.serverControlPanel.TabsWindow

@Preview
@Composable
fun ServerControlPanelWindow() {
	val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

	when(viewModel.serverControlPanelSettings.windowToDisplay.value) {
		ServerControlPanelWindows.TabsWindow -> TabsWindow()
		ServerControlPanelWindows.ConnectionInfoWindow -> ConnectionInfoWindow()
	}
}