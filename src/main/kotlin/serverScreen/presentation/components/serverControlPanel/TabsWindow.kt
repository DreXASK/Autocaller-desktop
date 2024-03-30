package serverScreen.presentation.components.serverControlPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.presentation.components.buttonTab.ButtonTabData
import core.presentation.components.buttonTab.ButtonTabMenuGrid
import core.utils.useNonBreakingSpace
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.ServerScreenViewModel

@Preview
@Composable
fun TabsWindow() {
    val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

    val buttonData = listOf(
        ButtonTabData(
            onClick = { },
            Icons.Rounded.List,
            text = "Текущие задания на сервере",
        ),
        ButtonTabData(
            onClick = { },
            Icons.Rounded.Add,
            text = "Шаблоны сообщений"
        ),
        ButtonTabData(
            onClick = { },
            Icons.Rounded.Edit,
            text = "Список подключенных клиентов"
        ),
        ButtonTabData(
            onClick = { },
            Icons.Rounded.Edit,
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
            OutlinedButton(
                onClick = {
                    viewModel.serverControlPanelSettings.windowToDisplay.value =
                        ServerControlPanelWindows.ConnectionInfoWindow
                },
                modifier = Modifier.align(Alignment.CenterEnd).padding(10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Rounded.Info,
                        null,
                        modifier = Modifier.padding(end = 5.dp)
                    )
                    Text("Соединение")
                }
            }
        }
        Divider()
        ButtonTabMenuGrid(
            buttonsDataList = buttonData,
            gridCells = GridCells.Fixed(4)
        )
    }

}