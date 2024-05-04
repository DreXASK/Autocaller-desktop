package serverScreen.presentation.components.serverControlPanel.tabs.statisticsWindow

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.presentation.DatePicker
import core.presentation.DateVisualTransformation
import core.presentation.components.OutlinedButtonWithIconText
import core.presentation.components.VerticalDivider
import org.koin.java.KoinJavaComponent.inject
import serverScreen.domain.models.StatisticsData
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.ServerControlPanelWindows
import java.time.LocalDate

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun StatisticsWindow() {
    val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

    val dateFrom = mutableStateOf<LocalDate?>(null)
    val dateTo = mutableStateOf<LocalDate?>(null)

    val dropdownMenuItemList = mutableStateListOf(
        StatisticsData("WhatsApp", 247, 56.7f),
        StatisticsData("Viber", 42, 8.3f)
    )
    var dropdownMenuSelected: StatisticsData? by remember { mutableStateOf(null) }
    var dropdownMenuExpanded by remember { mutableStateOf(false) }

    Row(Modifier.fillMaxSize()) {

        // Left column------------------------------------------------------------------------------------------

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButtonWithIconText(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(end = 5.dp),
                    icon = Icons.Rounded.ArrowBack,
                    text = "Назад в меню"
                ) {
                    viewModel.serverControlPanel.windowToDisplay.value =
                        ServerControlPanelWindows.TABS
                }

                ExposedDropdownMenuBox(
                    expanded = dropdownMenuExpanded,
                    onExpandedChange = { dropdownMenuExpanded = !dropdownMenuExpanded },
                    modifier = Modifier.weight(1f)
                ) {

                    OutlinedTextField(
                        value = dropdownMenuSelected?.name.orEmpty(),
                        onValueChange = { },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownMenuExpanded) }
                    )

                    ExposedDropdownMenu(
                        expanded = dropdownMenuExpanded,
                        onDismissRequest = { dropdownMenuExpanded = false }
                    ) {
                        dropdownMenuItemList.forEach { item ->
                            DropdownMenuItem(
                                onClick = {
                                    dropdownMenuExpanded = !dropdownMenuExpanded
                                    dropdownMenuSelected = item
                                }
                            ) {
                                Text(item.name)
                            }
                        }
                    }
                }
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 5.dp)
                ) {
                    Icon(Icons.Rounded.Refresh, "")
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),
                elevation = 20.dp
            ) {
                Column(modifier = Modifier.padding(5.dp)) {
                    TableStringRow("Отправлено сообщений", dropdownMenuSelected?.sentMessages.toString())
                    Divider()
                    TableStringRow(
                        "Процент прочитанных сообщений",
                        dropdownMenuSelected?.percentOfReadMessages.toString()
                    )
                }
            }
        }

        // Right column------------------------------------------------------------------------------------------

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 20.dp
            ) {
                Column {
                    Text(
                        "Фильтр",
                        Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Row(Modifier.height(IntrinsicSize.Max)) {
                        OutlinedTextField(
                            value = dateFrom.value?.toString() ?: "",
                            onValueChange = { },
                            enabled = false,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                                .clickable {
                                    DatePicker.setDataToState(dateFrom)
                                },
                            colors = TextFieldDefaults.textFieldColors(
                                disabledTextColor = LocalContentColor.current.copy(LocalContentAlpha.current),
                                disabledLabelColor =  MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
                            ),
                            label = { Text("Дата от") },
                        )
                        OutlinedTextField(
                            value = dateTo.value?.toString() ?: "",
                            onValueChange = { },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 10.dp, bottom = 10.dp)
                                .clickable {
                                    DatePicker.setDataToState(dateTo)
                                },
                            enabled = false,
                            label = { Text("Дата до") },
                            colors = TextFieldDefaults.textFieldColors(
                                disabledTextColor = LocalContentColor.current.copy(LocalContentAlpha.current),
                                disabledLabelColor =  MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
                            )
                        )
                    }
                }
            }
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 10.dp),
                elevation = 20.dp
            ) {
                Column {
                    Image(
                        painterResource("drawable/stats1.jpg"),
                        null,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                    )
                    Image(
                        painterResource("drawable/stats2.jpg"),
                        null,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
private fun TableStringRow(
    leftText: String,
    rightText: String
) {
    Row(Modifier.height(IntrinsicSize.Max)) {
        Text(
            text = leftText,
            modifier = Modifier.weight(0.8f)
        )
        VerticalDivider()
        Text(
            rightText,
            modifier = Modifier.weight(0.2f),
            textAlign = TextAlign.Center
        )
    }
}



