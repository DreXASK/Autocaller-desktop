package serverScreen.presentation.components.serverControlPanel.tabs.callProcessSettingsWindow

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.presentation.TimePicker
import core.presentation.components.OutlinedButtonWithIconText
import core.presentation.components.OutlinedButtonWithTextIcon
import core.presentation.components.VerticalDivider
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.ServerControlPanelWindows
import java.time.LocalTime

@Preview
@Composable
fun CallProcessSettingsWindow() {
    val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

    val timeFrom = mutableStateOf<LocalTime?>(null)
    val timeTo = mutableStateOf<LocalTime?>(null)

    Column(Modifier.fillMaxSize()) {
        Box(
            Modifier.fillMaxWidth().height(IntrinsicSize.Max)
        ) {
            OutlinedButtonWithIconText(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp),
                icon = Icons.Rounded.ArrowBack,
                text = "Назад в меню"
            ) {
                viewModel.serverControlPanel.windowToDisplay.value =
                    ServerControlPanelWindows.TABS
            }

            Text(
                text = "Настройки",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
                    .align(Alignment.Center),
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            OutlinedButtonWithTextIcon(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp)
                    .align(Alignment.CenterEnd),
                icon = Icons.Rounded.Done,
                text = "Применить настройки"
            ) {
                println("TODO()")
            }
        }
        Divider()
        Card {
            Row(Modifier.height(IntrinsicSize.Max)) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Настройка времени суток для совершения обзвона",
                        textAlign = TextAlign.Center
                    )
                }

                VerticalDivider()

                OutlinedTextField(
                    value = timeFrom.value?.toString() ?: "",
                    onValueChange = { },
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                        .clickable {
                            TimePicker.setTimeToState(timeFrom)
                        },
                    enabled = false,
                    label = { Text("Время от") },
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = LocalContentColor.current.copy(LocalContentAlpha.current),
                        disabledLabelColor = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
                    )
                )
                OutlinedTextField(
                    value = timeTo.value?.toString() ?: "",
                    onValueChange = { },
                    modifier = Modifier
                        .padding(end = 10.dp, bottom = 10.dp)
                        .clickable {
                            TimePicker.setTimeToState(timeTo)
                        },
                    enabled = false,
                    label = { Text("Время до") },
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = LocalContentColor.current.copy(LocalContentAlpha.current),
                        disabledLabelColor = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
                    )
                )
            }
        }
        Divider()
    }

}