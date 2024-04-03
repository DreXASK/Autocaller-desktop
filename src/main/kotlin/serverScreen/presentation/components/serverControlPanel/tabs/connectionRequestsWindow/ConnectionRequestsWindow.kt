package serverScreen.presentation.components.serverControlPanel.tabs.connectionRequestsWindow

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.presentation.components.OutlinedButtonWithIconText
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.ServerControlPanelWindows
import serverScreen.presentation.components.serverControlPanel.tabs.connectionRequestsWindow.connectionRequestsTable.ConnectionRequestsTableUI

@Preview
@Composable
fun ConnectionRequestsWindow() {
    val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max)
                .padding(start = 30.dp, top = 10.dp, end = 30.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Row {
                OutlinedButtonWithIconText(
                    modifier = Modifier.fillMaxHeight(),
                    icon = Icons.Rounded.ArrowBack,
                    text = "Назад в меню"
                ) {
                    viewModel.serverControlPanel.windowToDisplay.value =
                        ServerControlPanelWindows.Tabs
                }
            }

            OutlinedButton(
                onClick = { },
                modifier = Modifier.align(Alignment.TopEnd).fillMaxHeight()
            ) {
                Icon(Icons.Rounded.Refresh, "Refresh the table")
            }
        }

        ConnectionRequestsTableUI(
            viewModel.connectionRequestsTable.connectionRequestsList,
            contentPadding = PaddingValues(start = 30.dp, top = 10.dp, end = 30.dp, bottom = 30.dp)
        )
    }
}