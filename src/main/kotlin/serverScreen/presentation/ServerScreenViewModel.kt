package serverScreen.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import core.domain.ServerConnection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.components.serverControlPanel.ServerControlPanelSettings

class ServerScreenViewModel {
	val serverControlPanelSettings by inject<ServerControlPanelSettings>(ServerControlPanelSettings::class.java)
	val serverConnection by inject<ServerConnection>(ServerConnection::class.java)
}