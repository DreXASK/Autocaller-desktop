package serverScreen.domain.models

data class StatisticsData(
    val name: String,
    val sentMessages: Int,
    val percentOfReadMessages: Float
)