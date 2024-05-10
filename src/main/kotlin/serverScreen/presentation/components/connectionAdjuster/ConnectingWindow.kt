package serverScreen.presentation.components.connectionAdjuster

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import core.presentation.components.OutlinedButtonWithProgress
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.ServerScreenViewModel


@Preview
@Composable
fun ConnectingWindow() {
    val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

    OutlinedButtonWithProgress(
        onClick = {
            viewModel.abortConnectionProcess()
        },
        buttonText = { Text("Отмена подключения") }
    )
}