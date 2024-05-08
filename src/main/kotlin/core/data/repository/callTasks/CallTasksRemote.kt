package core.data.repository.callTasks

import core.domain.repository.callTasks.CallTasksTypes
import kotlinx.serialization.Serializable

@Serializable
data class CallTasksParameterGetRemote(
    val token: String,
): CallTasksTypes.Parameter.Get

@Serializable
data class CallTasksParameterRemoveRemote(
    val token: String,
    val id: Long
): CallTasksTypes.Parameter.Remove

@Serializable
data class CallTasksParameterSendRemote(
    val token: String,
    val list: List<CallTaskDto>
): CallTasksTypes.Parameter.Send
