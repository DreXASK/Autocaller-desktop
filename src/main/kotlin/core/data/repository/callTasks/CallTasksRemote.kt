package core.data.repository.callTasks

import core.domain.repository.callTasks.CallTaskDto
import core.domain.repository.callTasks.CallTaskTypes
import kotlinx.serialization.Serializable

@Serializable
data class CallTaskParameterGetRemote(
    val token: String
): CallTaskTypes.Parameter.Get

@Serializable
data class CallTaskParameterRemoveRemote(
    val token: String,
    val id: Long
): CallTaskTypes.Parameter.Remove

@Serializable
data class CallTaskParameterSendRemote(
    val token: String,
    val list: List<CallTaskDto>
): CallTaskTypes.Parameter.Send
