package core.domain.repository.callTasks

import core.domain.utils.ApiError
import core.domain.utils.Result

interface CallTaskRepository {
    suspend fun getCallTaskList(parameter: CallTaskTypes.Parameter.Get): Result<List<CallTaskDto>, ApiError.CallTasksError>

    suspend fun removeCallTask(parameter: CallTaskTypes.Parameter.Remove): Result<Unit, ApiError.CallTasksError>

    suspend fun sendCallTaskList(parameter: CallTaskTypes.Parameter.Send): Result<Unit, ApiError.CallTasksError>
}