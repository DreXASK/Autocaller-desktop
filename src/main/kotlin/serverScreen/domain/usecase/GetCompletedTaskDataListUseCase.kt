package serverScreen.domain.usecase

import core.domain.models.CallTaskData
import core.domain.repository.callTasks.CallTaskDto
import core.domain.repository.callTasks.CallTaskTypes
import core.domain.utils.ApiError
import core.domain.utils.Result
import serverScreen.domain.models.CompletedTaskData
import serverScreen.domain.repository.completedTasks.CompletedCallTaskTypes
import serverScreen.domain.repository.completedTasks.CompletedTaskDto
import serverScreen.domain.repository.completedTasks.CompletedTaskRepository
import java.time.Instant
import java.time.ZoneId

class GetCompletedTaskDataListUseCase(private val completedTaskRepository: CompletedTaskRepository) {

    suspend fun execute(callTaskDto: CompletedCallTaskTypes.Parameter.Get): Result<List<CompletedTaskData>, ApiError.CompletedCallTasksError> {
        return when (val result = completedTaskRepository.getCompletedCallTaskList(callTaskDto)) {
            is Result.Success -> Result.Success(result.data.transformToCompletedCallTaskData())
            is Result.Error -> Result.Error(result.error)
        }
    }

    private fun List<CompletedTaskDto>.transformToCompletedCallTaskData(): List<CompletedTaskData> {
        val resultList = mutableListOf<CompletedTaskData>()
        this.map {
            resultList.add(
                CompletedTaskData(
                    id = it.id,
                    surname = it.surname,
                    name = it.name,
                    patronymic = it.patronymic,
                    phoneNumber = it.phoneNumber,
                    messageText = it.messageText,
                    callAttempts = it.callAttempts,
                    isSmsUsed = it.isSmsUsed,
                    informTime = it.informTime.atZoneSameInstant(
                        ZoneId.systemDefault().rules.getOffset(Instant.now())
                    ).toLocalDateTime()
                )
            )
        }
        return resultList
    }

}