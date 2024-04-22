package serverScreen.presentation.components.connectionAdjuster

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import core.domain.ServerConnectionStatus
import core.presentation.components.OutLinedButtonWithProgress
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.ServerScreenViewModel


@Preview
@Composable
fun ConnectingWindow() {
    val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)
    var connectionStatus by remember { viewModel.serverConnection.connectionStatus }

    val abc = CoroutineScope(Dispatchers.Default).launch {
        delay(1000)
        connectionStatus = ServerConnectionStatus.CONNECTED
    }

    OutLinedButtonWithProgress(
        onClick = {
            connectionStatus = ServerConnectionStatus.DISCONNECTED
            abc.cancel()
        },
        buttonText = { Text("Отмена подключения") }
    )
}