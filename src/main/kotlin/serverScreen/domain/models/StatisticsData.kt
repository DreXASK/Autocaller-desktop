package serverScreen.domain.models

import java.time.LocalDate
import java.util.*

data class StatisticsData(
    var totalCalls: Int,
    var totalSuccessCalls: Int,
    var totalSms: Int,
    val averageCallsToSuccess: Float,
    val percentOfSms: Float,
    val callsByDate: SortedMap<LocalDate, Double>,
    val smsByDate: SortedMap<LocalDate, Double>,
    val commonDatesString: List<String>
)