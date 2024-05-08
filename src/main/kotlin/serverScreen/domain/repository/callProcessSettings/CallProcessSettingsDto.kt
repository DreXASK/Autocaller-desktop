package serverScreen.domain.repository.callProcessSettings

import androidx.compose.runtime.mutableStateOf
import core.domain.utils.OffsetDateTimeSerializer
import core.domain.utils.OffsetTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.OffsetTime

@Serializable
data class CallProcessSettingsDto(
    @Serializable(with = OffsetTimeSerializer::class) val timeFrom: OffsetTime,
    @Serializable(with = OffsetTimeSerializer::class) val timeTo: OffsetTime
)
