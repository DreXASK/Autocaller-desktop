package serverScreen.presentation.utils

import serverScreen.domain.models.CallProcessSettingsData
import serverScreen.domain.repository.callProcessSettings.CallProcessSettingsDto
import java.time.ZoneOffset

fun CallProcessSettingsData.transformToCallProcessSettingsDto(): CallProcessSettingsDto {
    return CallProcessSettingsDto(
        timeFrom = this.timeFrom.atOffset(ZoneOffset.UTC),
        timeTo = this.timeTo.atOffset(ZoneOffset.UTC)
    )
}