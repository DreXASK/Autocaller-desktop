package serverScreen.presentation.components.serverControlPanel.tabs.statisticsWindow

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aay.compose.barChart.BarChart
import com.aay.compose.barChart.model.BarParameters
import core.presentation.DatePicker
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
                                disabledLabelColor = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
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
                                disabledLabelColor = MaterialTheme.colors.onSurface.copy(ContentAlpha.medium)
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
//                    val testLineParameters = mutableStateListOf(
//                        LineParameters(
//                            label = "revenue",
//                            data = listOf(70.0, 00.0, 50.33, 40.0, 100.500, 50.0, 1.0, 2.0, 3.0, 4.0, 5.0),
//                            lineColor = Color.Gray,
//                            lineType = LineType.CURVED_LINE,
//                            lineShadow = true,
//                        ),
//                        LineParameters(
//                            label = "Earnings",
//                            data = listOf(60.0, 80.6, 40.33, 86.232, 88.0, 90.0, 1.0, 2.0, 3.0, 4.0, 5.0),
//                            lineColor = Color(0xFFFF7F50),
//                            lineType = LineType.DEFAULT_LINE,
//                            lineShadow = true
//                        ),
//                        LineParameters(
//                            label = "Earnings",
//                            data = listOf(1.0, 40.0, 11.33, 55.23, 1.0, 100.0, 1.0, 2.0, 3.0, 4.0, 5.0),
//                            lineColor = Color(0xFF81BE88),
//                            lineType = LineType.CURVED_LINE,
//                            lineShadow = false,
//                        )
//                    )
//
//                    LineChart(
//                        modifier = Modifier.fillMaxSize(),
//                        linesParameters = testLineParameters,
//                        isGrid = true,
//                        gridColor = Color.Blue,
//                        xAxisData = listOf("2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"),
//                        animateChart = true,
//                        showGridWithSpacer = true,
//                        yAxisStyle = TextStyle(
//                            fontSize = 14.sp,
//                            color = Color.Gray,
//                        ),
//                        xAxisStyle = TextStyle(
//                            fontSize = 14.sp,
//                            color = Color.Gray,
//                            fontWeight = FontWeight.W400
//                        ),
//                        yAxisRange = 14,
//                        oneLineChart = false,
//                        gridOrientation = GridOrientation.VERTICAL
//                    )

                val testBarParameters = mutableStateListOf(
                    BarParameters(
                        dataName = "Completed",
                        data = listOf(0.6, 10.6, 80.0, 50.6, 44.0, 100.6, 10.0),
                        barColor = MaterialTheme.colors.primary
                    ),
                    BarParameters(
                        dataName = "Completed",
                        data = listOf(50.0, 30.6, 77.0, 69.6, 50.0, 30.6, 80.0),
                        barColor = MaterialTheme.colors.surface,
                    ),
//                        BarParameters(
//                            dataName = "Completed",
//                            data = listOf(100.0, 99.6, 60.0, 80.6, 10.0, 100.6, 55.99),
//                            barColor = Color(0xFFDFA878),
//                        ),
                )

                Box(modifier = Modifier.padding(10.dp)) {
                    BarChart(
                        chartParameters = testBarParameters,
                        gridColor = MaterialTheme.colors.background,
                        xAxisData = listOf("2016", "2017", "2018", "2019", "2020", "2021", "2022"),
                        isShowGrid = true,
                        animateChart = true,
                        showGridWithSpacer = true,
                        yAxisStyle = TextStyle(
                            fontSize = 14.sp,
                            color = MaterialTheme.colors.primary,
                        ),
                        xAxisStyle = TextStyle(
                            fontSize = 14.sp,
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.W400
                        ),
                        barCornerRadius = 5.dp,
                        yAxisRange = 15,
                        barWidth = 30.dp
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



