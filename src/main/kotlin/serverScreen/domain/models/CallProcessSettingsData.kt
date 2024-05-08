package serverScreen.domain.models

import java.time.LocalTime

data class CallProcessSettingsData(
    val timeFrom: LocalTime,
    val timeTo: LocalTime
)
