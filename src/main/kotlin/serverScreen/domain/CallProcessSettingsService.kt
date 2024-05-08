package serverScreen.domain

import androidx.compose.runtime.mutableStateOf
import core.domain.usecase.messageTemplates.GetMessageTemplatesUseCase
import core.domain.usecase.messageTemplates.SendMessageTemplateUseCase
import core.domain.utils.ApiError
import core.domain.utils.Result
import org.koin.java.KoinJavaComponent.get
import serverScreen.data.repository.callProcessSettings.CallProcessSettingsParameterGetRemote
import serverScreen.data.repository.callProcessSettings.CallProcessSettingsParameterSendRemote
import serverScreen.domain.models.CallProcessSettingsData
import serverScreen.domain.usecase.callProcessSettings.GetCallProcessSettingsUseCase
import serverScreen.domain.usecase.callProcessSettings.SendCallProcessSettingsUseCase
import java.time.LocalTime

class CallProcessSettingsService {

    val timeFrom = mutableStateOf<LocalTime?>(null)
    val timeTo = mutableStateOf<LocalTime?>(null)

    suspend fun getSettingsFromServer(settingsDtoParameter: CallProcessSettingsParameterGetRemote): Result<CallProcessSettingsData, ApiError.CallProcessSettings> {
        val useCase = get<GetCallProcessSettingsUseCase>(GetCallProcessSettingsUseCase::class.java)
        val result = useCase.execute(settingsDtoParameter)
        return result
    }

    suspend fun sendSettingsToServer(settingsDtoParameter: CallProcessSettingsParameterSendRemote): Result<Unit, ApiError.CallProcessSettings> {
        val useCase = get<SendCallProcessSettingsUseCase>(SendCallProcessSettingsUseCase::class.java)
        val result = useCase.execute(settingsDtoParameter)
        return result
    }

    fun setupSettings(settingsData: CallProcessSettingsData) {
        timeFrom.value = settingsData.timeFrom
        timeTo.value = settingsData.timeTo
    }

    fun getSettingsData(): CallProcessSettingsData? {

        val timeFrom = timeFrom.value
        val timeTo = timeTo.value

        if (timeFrom == null || timeTo == null)
            return null

        return CallProcessSettingsData(
            timeFrom = timeFrom,
            timeTo = timeTo
        )
    }


}