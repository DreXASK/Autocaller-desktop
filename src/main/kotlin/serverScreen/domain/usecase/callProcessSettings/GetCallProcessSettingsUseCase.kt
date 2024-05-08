package serverScreen.domain.usecase.callProcessSettings

import core.domain.utils.ApiError
import core.domain.utils.Result
import serverScreen.domain.models.CallProcessSettingsData
import serverScreen.domain.repository.callProcessSettings.CallProcessSettingsDto
import serverScreen.domain.repository.callProcessSettings.CallProcessSettingsRepository
import serverScreen.domain.repository.callProcessSettings.CallProcessSettingsTypes
import java.time.Instant
import java.time.ZoneId

class GetCallProcessSettingsUseCase(private val callProcessSettingsRepository: CallProcessSettingsRepository) {

    suspend fun execute(parameter: CallProcessSettingsTypes.Parameter.Get): Result<CallProcessSettingsData, ApiError.CallProcessSettings> {
        return when (val result = callProcessSettingsRepository.getCallProcessSettings(parameter)) {
            is Result.Success -> Result.Success(result.data.transformToCallProcessSettingsData())
            is Result.Error -> Result.Error(result.error)
        }
    }

    private fun CallProcessSettingsDto.transformToCallProcessSettingsData(): CallProcessSettingsData {
        return CallProcessSettingsData(
            timeFrom = this.timeFrom.withOffsetSameLocal(ZoneId.systemDefault().rules.getOffset(Instant.now())).toLocalTime(),
            timeTo = this.timeTo.withOffsetSameLocal(ZoneId.systemDefault().rules.getOffset(Instant.now())).toLocalTime()
        )
    }
}

//timeFrom.atZoneSameInstant(
//                    ZoneId.systemDefault().rules.getOffset(Instant.now()),