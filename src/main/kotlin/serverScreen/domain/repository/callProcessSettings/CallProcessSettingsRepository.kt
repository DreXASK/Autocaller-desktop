package serverScreen.domain.repository.callProcessSettings

import core.domain.utils.ApiError
import core.domain.utils.Result

interface CallProcessSettingsRepository {

    suspend fun getCallProcessSettings(parameter: CallProcessSettingsTypes.Parameter.Get): Result<CallProcessSettingsDto, ApiError.CallProcessSettings>

    suspend fun sendCallProcessSettings(parameter: CallProcessSettingsTypes.Parameter.Send): Result<Unit, ApiError.CallProcessSettings>

}