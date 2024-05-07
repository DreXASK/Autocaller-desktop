package core.data.repository.callTasks

import callScreen.domain.models.ContactData
import core.domain.models.CallTaskData
import core.domain.repository.callTasks.CallTasksDto
import kotlinx.serialization.Serializable

@Serializable
data class CallTasksParameterSendRemote(
    val token: String,
    val list: List<CallTaskData>
): CallTasksDto.Parameter.Send