package serverScreen.presentation.utils

import serverScreen.domain.models.CallProcessSettingsData
import serverScreen.domain.repository.callProcessSettings.CallProcessSettingsDto
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset

fun CallProcessSettingsData.transformToCallProcessSettingsDto(): CallProcessSettingsDto {
    return CallProcessSettingsDto(
        timeFrom = this.timeFrom.atOffset(ZoneOffset.UTC),
        timeTo = this.timeTo.atOffset(ZoneOffset.UTC)
    )
}

fun CallProcessSettingsDto.transformToCallProcessSettingsData(): CallProcessSettingsData {
    return CallProcessSettingsData(
        timeFrom = this.timeFrom.withOffsetSameLocal(ZoneId.systemDefault().rules.getOffset(Instant.now())).toLocalTime(),
        timeTo = this.timeTo.withOffsetSameLocal(ZoneId.systemDefault().rules.getOffset(Instant.now())).toLocalTime()
    )
}