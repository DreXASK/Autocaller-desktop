package serverScreen.presentation.components.serverControlPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.presentation.components.buttonTab.ButtonTabData
import core.presentation.components.buttonTab.ButtonTabMenuGrid
import core.utils.useNonBreakingSpace
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.ServerScreenViewModel

@Preview
@Composable
fun TabsWindow() {
    val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

    val buttonData = listOf(
        ButtonTabData(
            { },
            Icons.Rounded.List,
            text = "Кнопка 1".useNonBreakingSpace(),
        ),
        ButtonTabData(
            { },
            Icons.Rounded.Add,
            text = "Кнопка 2".useNonBreakingSpace()
        ),
        ButtonTabData(
            { },
            Icons.Rounded.Edit,
            text = "Кнопка 3".useNonBreakingSpace()
        ),
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
                {
                viewModel.serverControlPanelSettings.windowToDisplay.value = ServerControlPanelWindows.UserProfileWindow
                },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Rounded.AccountCircle,
                        null,
                        modifier = Modifier.padding(10.dp)
                    )
                    Text("Администратор")
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