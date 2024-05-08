package core.domain.repository.callTasks

import core.data.repository.callTasks.CallTaskDto
import core.domain.utils.ApiError
import core.domain.utils.Result

interface CallTaskRepository {
    suspend fun getCallTaskList(parameter: CallTasksTypes.Parameter.Get): Result<List<CallTaskDto>, ApiError.CallTasksError>

    suspend fun removeCallTask(parameter: CallTasksTypes.Parameter.Remove): Result<Unit, ApiError.CallTasksError>

    suspend fun sendCallTaskList(parameter: CallTasksTypes.Parameter.Send): Result<Unit, ApiError.CallTasksError>
}