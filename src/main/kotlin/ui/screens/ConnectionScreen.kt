package ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import network.Connection
import network.ConnectionStatus
import ui.components.ConnectionAdjuster
import ui.screenModes.ConnectionScreenModes
import ui.screenModes.MainScreenModes

@Preview
@Composable
fun ConnectionScreen() {

    val mode = mutableStateOf(ConnectionScreenModes.Disconnected)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (mode.value) {
            ConnectionScreenModes.Connected ->
                Text("Подключение успешно")
            ConnectionScreenModes.Disconnected ->
                ConnectionAdjuster()
        }

    }
}