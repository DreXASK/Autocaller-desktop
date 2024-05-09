package serverScreen.presentation.components.serverControlPanel.tabs.statisticsWindow

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.aay.compose.donutChart.DonutChart
import com.aay.compose.donutChart.PieChart
import com.aay.compose.donutChart.model.PieChartData
import core.presentation.DatePicker
import core.presentation.components.OutlinedButtonWithIconText
import core.presentation.components.VerticalDivider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import serverScreen.domain.models.CompletedTaskData
import serverScreen.domain.models.StatisticsData
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.ServerControlPanelWindows
import java.time.LocalDate

@Preview
@Composable
fun StatisticsWindow() {
    val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

    val dateFrom = mutableStateOf<LocalDate?>(null)
    val dateTo = mutableStateOf<LocalDate?>(null)
    var statisticsData by remember { mutableStateOf<StatisticsData?>(null) }

    LaunchedEffect(Unit) {
        val list = viewModel.getCompletedTaskListFromServer()
        statisticsData = list?.let { getStatisticsData(it, dateFrom.value, dateTo.value) }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        item {
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

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
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

                OutlinedButton(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            val list = viewModel.getCompletedTaskListFromServer()
                            statisticsData = list?.let { getStatisticsData(it, dateFrom.value, dateTo.value) }
                        }
                    },
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 5.dp)
                ) {
                    Icon(Icons.Rounded.Refresh, "")
                }
            }
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),
                elevation = 20.dp
            ) {
                Column(modifier = Modifier.padding(5.dp)) {
                    TableStringRow("Всего звонков совершено", statisticsData?.totalCalls?.toString() ?: "")
                    Divider()
                    TableStringRow("Всего успешных звонков", statisticsData?.totalSuccessCalls?.toString() ?: "")
                    Divider()
                    TableStringRow("Всего отправлено смс", statisticsData?.totalSms?.toString() ?: "")
                    Divider()
                    TableStringRow(
                        "Среднее количество вызовов до дозвона",
                        statisticsData?.averageCallsToSuccess?.let {
                            "%.2f".format(it)
                        } ?: ""
                    )
                }
            }
        }

        item {
            Card(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .height(500.dp),
                elevation = 20.dp
            ) {
                statisticsData?.let { statisticsData ->
                    val testBarParameters = mutableStateListOf(
                        BarParameters(
                            dataName = "Звонки",
                            data = statisticsData.callsByDate.values.toList(),
                            barColor = MaterialTheme.colors.primary
                        ),
                        BarParameters(
                            dataName = "SMS",
                            data = statisticsData.smsByDate.values.toList(),
                            barColor = MaterialTheme.colors.surface,
                        ),
                    )
                    Box(Modifier.padding(10.dp)) {
                        BarChart(
                            chartParameters = testBarParameters,
                            gridColor = MaterialTheme.colors.background,
                            xAxisData = statisticsData.commonDatesString,
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
                            yAxisRange = 10,
                            barWidth = 30.dp
                        )
                    }
                }

            }
        }

        item {

            Row(modifier = Modifier.height(500.dp)) {
                Card(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .weight(1f),
                    elevation = 20.dp
                ) {

                    statisticsData?.let { statisticsData ->

                        val testPieChartData: List<PieChartData> = listOf(
                            PieChartData(
                                partName = "Звонки",
                                data = statisticsData.totalCalls.toDouble(),
                                color = MaterialTheme.colors.primary,
                            ),
                            PieChartData(
                                partName = "SMS",
                                data = statisticsData.totalSms.toDouble(),
                                color = MaterialTheme.colors.surface,
                            )
                        )

                        DonutChart(
                            modifier = Modifier.fillMaxSize(),
                            pieChartData = testPieChartData,
                            centerTitle = "Метод",
                            centerTitleStyle = TextStyle(color = MaterialTheme.colors.primary),
                            outerCircularColor = MaterialTheme.colors.surface,
                            innerCircularColor = MaterialTheme.colors.primary,
                            ratioLineColor = MaterialTheme.colors.background,
                        )
                    }

                }

                Spacer(Modifier.width(10.dp))

                Card(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .weight(1f),
                    elevation = 20.dp
                ) {

                    statisticsData?.let { statisticsData ->

                        val testPieChartData: List<PieChartData> = listOf(
                            PieChartData(
                                partName = "Звонки",
                                data = statisticsData.totalCalls.toDouble(),
                                color = MaterialTheme.colors.primary,
                            ),
                            PieChartData(
                                partName = "Успешные звонки",
                                data = statisticsData.totalSuccessCalls.toDouble(),
                                color = MaterialTheme.colors.surface,
                            )
                        )

                        PieChart(
                            modifier = Modifier.fillMaxSize(),
                            pieChartData = testPieChartData,
                            outerCircularColor = MaterialTheme.colors.surface,
                            ratioLineColor = MaterialTheme.colors.background,
                        )
                    }

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

private fun getStatisticsData(
    completedTaskDataList: List<CompletedTaskData>,
    dataFrom: LocalDate?,
    dateTo: LocalDate?
): StatisticsData {
    val callsByDate = mutableMapOf<LocalDate, Double>()
    val smsByDate = mutableMapOf<LocalDate, Double>()

    var totalCalls = 0
    var totalSuccessCalls = 0
    var totalSms = 0

    completedTaskDataList.map {
        val date = it.informDateTime.toLocalDate()

        if (dataFrom != null && date < dataFrom)
            return@map
        if (dateTo != null && date > dateTo)
            return@map

        when (it.isSmsUsed) {
            true -> {
                smsByDate[date] = smsByDate[date]?.plus(1) ?: 1.0
                callsByDate[date] = smsByDate[date] ?: 0.0
            }

            false -> {
                callsByDate[date] = callsByDate[date]?.plus(1) ?: 1.0
                smsByDate[date] = smsByDate[date] ?: 0.0
            }
        }

        totalCalls += it.callAttempts

        if (it.isSmsUsed) {
            totalSms += 1
        } else {
            totalSuccessCalls += 1
        }
    }

    val averageCallsToSuccess = if (totalCalls != 0) totalCalls.toFloat() / totalSuccessCalls else 0.0f
    val percentOfSms = if (totalCalls != 0) totalSms.toFloat() / totalCalls else 0.0f

    val sortedCallsByDate = callsByDate.toSortedMap()
    val sortedSmsByDate = smsByDate.toSortedMap()
    val commonDatesString = sortedSmsByDate.keys.map { it.toString() }.toList()

    return StatisticsData(
        totalCalls = totalCalls,
        totalSuccessCalls = totalSuccessCalls,
        totalSms = totalSms,
        averageCallsToSuccess = averageCallsToSuccess,
        percentOfSms = percentOfSms,
        callsByDate = sortedCallsByDate,
        smsByDate = sortedSmsByDate,
        commonDatesString = commonDatesString
    )
}