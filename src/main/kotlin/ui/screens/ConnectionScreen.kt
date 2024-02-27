package ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ui.components.ConnectionAdjuster
import ui.screenModes.ConnectionScreenModes
import viewModels.ConnectionScreenViewModel

@Preview
@Composable
fun ConnectionScreen(viewModel: ConnectionScreenViewModel) {

    val screenMode by remember { viewModel.screenMode }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (screenMode) {
            ConnectionScreenModes.Connected ->
                Text("Подключение успешно")
            ConnectionScreenModes.Disconnected ->
                ConnectionAdjuster(viewModel.ipTextValue)
        }
    }
}