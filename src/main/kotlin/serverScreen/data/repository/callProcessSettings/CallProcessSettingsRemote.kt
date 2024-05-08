package serverScreen.data.repository.callProcessSettings

import kotlinx.serialization.Serializable
import serverScreen.domain.repository.callProcessSettings.CallProcessSettingsDto
import serverScreen.domain.repository.callProcessSettings.CallProcessSettingsTypes
import serverScreen.domain.repository.completedTasks.CompletedCallTaskTypes

@Serializable
data class CallProcessSettingsParameterGetRemote(
    val token: String
): CallProcessSettingsTypes.Parameter.Get

@Serializable
data class CallProcessSettingsParameterSendRemote(
    val token: String,
    val settings: CallProcessSettingsDto
): CallProcessSettingsTypes.Parameter.Send
