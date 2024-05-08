package serverScreen.domain.usecase

import core.domain.repository.callTasks.CallTaskRepository
import core.domain.repository.callTasks.CallTasksTypes
import core.domain.utils.ApiError
import core.domain.utils.Result

class RemoveCallTaskUseCase(private val callTaskRepository: CallTaskRepository) {

    suspend fun execute(parameter: CallTasksTypes.Parameter.Remove): Result<Unit, ApiError.CallTasksError> {
        val result = callTaskRepository.removeCallTask(parameter)
        return result
    }

}