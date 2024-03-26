package connectionAdjusterScreen.presentation.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import connectionAdjusterScreen.presentation.ConnectionAdjusterScreenViewModel
import core.domain.ServerConnectionStatus
import org.koin.java.KoinJavaComponent.inject

@Preview
@Composable
fun DisconnectedWindow() {
    val viewModel by inject<ConnectionAdjusterScreenViewModel>(ConnectionAdjusterScreenViewModel::class.java)
    var ipTextValue by remember { viewModel.ipTextValue }
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
        Button(
            onClick = {
                connectionStatus = ServerConnectionStatus.Connecting
                viewModel.getToken()
                viewModel.checkTokenStatus()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Подключиться")
        }
        Text(connectionStatus.text)
    }
}
