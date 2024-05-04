package serverScreen.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import core.domain.ServerConnection
import core.domain.ServerConnectionStatus
import org.koin.java.KoinJavaComponent.get
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.components.connectionAdjuster.ConnectionAdjusterScreen
import serverScreen.presentation.components.serverControlPanel.ServerControlPanelScreen


@Preview
@Composable
fun ServerScreen() {
	val connection by inject<ServerConnection>(ServerConnection::class.java)

	when(connection.connectionStatus.value) {
		ServerConnectionStatus.CONNECTED -> ServerControlPanelScreen()
		else -> ConnectionAdjusterScreen()
	}
}