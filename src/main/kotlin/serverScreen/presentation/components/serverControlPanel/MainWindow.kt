package serverScreen.presentation.components.serverControlPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.ServerScreenViewModel

@Preview
@Composable
fun ServerControlPanelWindow() {
	val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

	when(viewModel.serverControlPanelSettings.windowToDisplay.value) {
		ServerControlPanelWindows.ServerControlPanel -> TabsWindow()
		ServerControlPanelWindows.UserProfileWindow -> UserProfileWindow()
	}
}