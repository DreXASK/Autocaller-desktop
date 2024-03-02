package connectionAdjusterScreen.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.java.KoinJavaComponent.inject
import connectionAdjusterScreen.presentation.components.SettingConnection
import core.domain.ServerConnectionStatus

@Preview
@Composable
fun ConnectionAdjusterScreen() {
    val viewModel by inject<ConnectionAdjusterScreenViewModel>(ConnectionAdjusterScreenViewModel::class.java)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (viewModel.serverConnection.connectionStatus.value) {
            ServerConnectionStatus.Disconnected ->
                SettingConnection()
            ServerConnectionStatus.Connecting ->
                TODO()
            ServerConnectionStatus.PendingToken ->
                TODO()
            ServerConnectionStatus.Connected ->
                Text("Подключение успешно")
        }
    }
}