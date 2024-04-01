package serverScreen.presentation.components.serverControlPanel.tabs

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.presentation.components.OutlinedButtonWithIconText
import core.presentation.components.buttonTab.ButtonTabData
import core.presentation.components.buttonTab.ButtonTabMenuGrid
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.ServerControlPanelWindows

@Preview
@Composable
fun MainTabsWindow() {
    val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

    val buttonData = listOf(
        ButtonTabData(
            onClick = {
                      viewModel.serverControlPanel.windowToDisplay.value = ServerControlPanelWindows.Tasks
            },
            Icons.Rounded.Phone,
            text = "Текущие задания на сервере",
        ),
        ButtonTabData(
            onClick = { },
            Icons.Rounded.Edit,
            text = "Шаблоны сообщений"
        ),
        ButtonTabData(
            onClick = { },
            Icons.Rounded.Notifications,
            text = "Статистика информирования"
        ),
        ButtonTabData(
            onClick = {
                viewModel.serverControlPanel.windowToDisplay.value = ServerControlPanelWindows.ListOfConnectedClients
            },
            Icons.Rounded.List,
            text = "Список подключенных клиентов"
        ),
        ButtonTabData(
            onClick = {
                viewModel.serverControlPanel.windowToDisplay.value = ServerControlPanelWindows.ConnectionRequestsList
            },
            Icons.Rounded.List,
            text = "Запросы на подключение"
        )
    )


    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier.fillMaxWidth()
        ) {
            Text(
                "Панель управления сервером",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 25.sp
            )
            OutlinedButtonWithIconText(
                modifier = Modifier.align(Alignment.CenterEnd).padding(10.dp),
                icon = Icons.Rounded.Info,
                text = "Соединение"
            ) {
                viewModel.serverControlPanel.windowToDisplay.value =
                    ServerControlPanelWindows.ConnectionInfoWindow
            }
        }
        Divider()
        ButtonTabMenuGrid(
            buttonsDataList = buttonData,
            gridCells = GridCells.Fixed(4)
        )
    }

}