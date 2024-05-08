package serverScreen.domain.usecase.callProcessSettings

import core.domain.utils.ApiError
import core.domain.utils.Result
import serverScreen.domain.models.CallProcessSettingsData
import serverScreen.domain.repository.callProcessSettings.CallProcessSettingsDto
import serverScreen.domain.repository.callProcessSettings.CallProcessSettingsRepository
import serverScreen.domain.repository.callProcessSettings.CallProcessSettingsTypes
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset

class SendCallProcessSettingsUseCase(private val callProcessSettingsRepository: CallProcessSettingsRepository) {

    suspend fun execute(parameter: CallProcessSettingsTypes.Parameter.Send): Result<Unit, ApiError.CallProcessSettings> {
        return when (val result = callProcessSettingsRepository.sendCallProcessSettings(parameter)) {
            is Result.Success -> Result.Success(result.data)
            is Result.Error -> Result.Error(result.error)
        }
    }

//    private fun CallProcessSettingsData.transformToCallProcessSettingsDto(): CallProcessSettingsDto {
//        return CallProcessSettingsDto(
//            timeFrom = this.timeFrom.atOffset(ZoneOffset.UTC),
//            timeTo = this.timeTo.atOffset(ZoneOffset.UTC)
//        )
//    }
}