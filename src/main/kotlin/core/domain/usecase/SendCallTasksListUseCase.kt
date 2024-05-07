package core.domain.usecase

import core.domain.utils.ApiError
import core.domain.utils.Result
import core.domain.repository.callTasks.CallTasksDto
import core.domain.repository.callTasks.CallTaskRepository

class SendCallTasksListUseCase(private val callTaskRepository: CallTaskRepository) {

    suspend fun execute(callTasksDto: CallTasksDto.Parameter.Send): Result<Unit, ApiError.ContactsError> {
        val result = callTaskRepository.sendContactList(callTasksDto)
        return result
    }

}