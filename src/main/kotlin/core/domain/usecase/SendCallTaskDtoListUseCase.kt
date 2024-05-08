package core.domain.usecase

import core.domain.utils.ApiError
import core.domain.utils.Result
import core.domain.repository.callTasks.CallTasksTypes
import core.domain.repository.callTasks.CallTaskRepository

class SendCallTaskDtoListUseCase(private val callTaskRepository: CallTaskRepository) {

    suspend fun execute(callTaskDto: CallTasksTypes.Parameter.Send): Result<Unit, ApiError.CallTasksError> {
        val result = callTaskRepository.sendCallTaskList(callTaskDto)
        return result
    }

}