package connectionAdjusterScreen.presentation.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import connectionAdjusterScreen.presentation.ConnectionAdjusterScreenViewModel
import core.domain.ServerConnectionStatus
import core.presentation.components.OutLinedButtonWithProgress
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent



@Preview
@Composable
fun ConnectingWindow() {
    val viewModel by KoinJavaComponent.inject<ConnectionAdjusterScreenViewModel>(ConnectionAdjusterScreenViewModel::class.java)
    var connectionStatus by remember { viewModel.serverConnection.connectionStatus }

    val abc = CoroutineScope(Dispatchers.Default).launch {
        delay(3000)
        connectionStatus = ServerConnectionStatus.Connected
    }

    OutLinedButtonWithProgress(
        onClick = {
            connectionStatus = ServerConnectionStatus.Disconnected
            abc.cancel()
        },
        buttonText = { Text("Отмена подключения") }
    )
}