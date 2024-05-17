package serverScreen.domain.usecase.callProcessSettings

import core.domain.utils.ApiError
import core.domain.utils.Result
import serverScreen.domain.models.CallProcessSettingsData
import serverScreen.domain.repository.callProcessSettings.CallProcessSettingsRepository
import serverScreen.domain.repository.callProcessSettings.CallProcessSettingsTypes
import serverScreen.presentation.utils.transformToCallProcessSettingsData

class GetCallProcessSettingsUseCase(private val callProcessSettingsRepository: CallProcessSettingsRepository) {

    suspend fun execute(parameter: CallProcessSettingsTypes.Parameter.Get): Result<CallProcessSettingsData, ApiError.CallProcessSettings> {
        return when (val result = callProcessSettingsRepository.getCallProcessSettings(parameter)) {
            is Result.Success -> Result.Success(result.data.transformToCallProcessSettingsData())
            is Result.Error -> Result.Error(result.error)
        }
    }
}