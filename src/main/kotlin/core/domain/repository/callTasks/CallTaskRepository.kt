package core.domain.repository.callTasks

import core.domain.utils.ApiError
import core.domain.utils.Result
import core.domain.models.CallTaskData

interface CallTaskRepository {
    suspend fun getContactList(parameter: CallTasksDto.Parameter.Get): Result<List<CallTaskData>, ApiError.ContactsError>

    suspend fun sendContactList(parameter: CallTasksDto.Parameter.Send): Result<Unit, ApiError.ContactsError>
}