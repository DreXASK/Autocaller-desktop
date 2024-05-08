package serverScreen.data.repository.completedCallTasks

import kotlinx.serialization.Serializable
import serverScreen.domain.repository.completedTasks.CompletedCallTaskTypes

@Serializable
data class CompletedCallTaskParameterGetRemote(
    val token: String
): CompletedCallTaskTypes.Parameter.Get
