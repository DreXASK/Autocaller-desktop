package serverScreen.domain.models

import java.time.LocalDate
import java.util.*

data class StatisticsData(
    val totalCalls: Int,
    val totalSuccessCalls: Int,
    val totalSms: Int,
    val averageCallsToSuccess: Float,
    val percentOfSms: Float,
    val callsByDate: SortedMap<LocalDate, Double>,
    val smsByDate: SortedMap<LocalDate, Double>,
    val commonDatesString: List<String>
)