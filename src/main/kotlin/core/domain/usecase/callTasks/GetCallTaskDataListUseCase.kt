package core.domain.usecase.callTasks

import core.domain.repository.callTasks.CallTaskDto
import core.domain.models.CallTaskData
import core.domain.repository.callTasks.CallTaskRepository
import core.domain.repository.callTasks.CallTaskTypes
import core.domain.utils.ApiError
import core.domain.utils.Result
import java.time.Instant
import java.time.ZoneId

class GetCallTaskDataListUseCase(private val callTaskRepository: CallTaskRepository) {

    suspend fun execute(callTaskDto: CallTaskTypes.Parameter.Get): Result<List<CallTaskData>, ApiError.CallTasksError> {
        return when(val result = callTaskRepository.getCallTaskList(callTaskDto)) {
            is Result.Success -> Result.Success(result.data.transformToCallTaskData())
            is Result.Error -> Result.Error(result.error)
        }
    }

    private fun List<CallTaskDto>.transformToCallTaskData(): List<CallTaskData> {
        val resultList = mutableListOf<CallTaskData>()
        this.map {
            resultList.add(CallTaskData(
                id = it.id,
                surname = it.surname,
                name = it.name,
                patronymic = it.patronymic,
                phoneNumber = it.phoneNumber,
                messageText = it.messageText,
                callAttempts = it.callAttempts,
                nextCallDateAndTime = it.nextCallDateTimeUTC.atZoneSameInstant(
                    ZoneId.systemDefault().rules.getOffset(Instant.now())).toLocalDateTime(),
            ))
        }
        return resultList
    }

}