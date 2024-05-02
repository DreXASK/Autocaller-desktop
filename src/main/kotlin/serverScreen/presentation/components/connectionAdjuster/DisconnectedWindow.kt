package serverScreen.presentation.components.connectionAdjuster

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import core.domain.ServerConnectionStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.ServerScreenViewModel

const val DISCONNECTED_BUTTON_TAG = "DISCONNECTED_BUTTON_TAG"

@Preview
@Composable
fun DisconnectedWindow() {
    val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)
    var ipTextValue by remember { mutableStateOf("localhost:8080") }
    var connectionKeyTextValue by remember { mutableStateOf("") }
    var connectionStatus by remember { viewModel.serverConnection.connectionStatus }

    Column(
        modifier = Modifier.width(IntrinsicSize.Min),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = ipTextValue,
            onValueChange = { ipTextValue = it },
            label = { Text("IP:Port") }
        )
        OutlinedTextField(
            value = connectionKeyTextValue,
            onValueChange = { connectionKeyTextValue = it },
            label = { Text("Ключ аутентификации") }
        )
        Button(
            onClick = {
                CoroutineScope(Dispatchers.Default).launch {
                    connectionStatus = ServerConnectionStatus.CONNECTING
                    viewModel.serverConnection.getToken()
                    viewModel.serverConnection.getConnectionStatus()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag(DISCONNECTED_BUTTON_TAG)
        ) {
            Text("Подключиться к серверу")
        }
    }
}
