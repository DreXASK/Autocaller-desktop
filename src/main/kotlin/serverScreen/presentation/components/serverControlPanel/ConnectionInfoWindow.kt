package serverScreen.presentation.components.serverControlPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.utils.useNonBreakingSpace
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.ServerScreenViewModel

@Preview
@Composable
fun ConnectionInfoWindow() {
    val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.width(IntrinsicSize.Max),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Клиент подключен к серверу".useNonBreakingSpace(),
                modifier = Modifier.padding(bottom = 10.dp),
                fontSize = 20.sp
            )
            Card(
                elevation = 10.dp
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "IP-адрес сервера: TODO TODO TODO".useNonBreakingSpace(),
                        modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp),
                        maxLines = 1,
                        softWrap = false
                    ) // TODO("Вписать сюда IP")
                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        colors = ButtonDefaults.outlinedButtonColors(Color.Red)
                    ) {
                        Text("Отвязать клиент от системы".useNonBreakingSpace())
                    }
                }
            }
            OutlinedButton(
                onClick = {
                    viewModel.serverControlPanelSettings.windowToDisplay.value = ServerControlPanelWindows.TabsWindow
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Назад")
            }
        }
    }
}