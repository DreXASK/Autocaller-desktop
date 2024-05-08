package serverScreen.domain.repository.completedTasks

import core.domain.utils.ApiError
import core.domain.utils.Result

interface CompletedTaskRepository {

    suspend fun getCompletedCallTaskList(parameter: CompletedCallTaskTypes.Parameter.Get): Result<List<CompletedTaskDto>, ApiError.CompletedCallTasksError>

}