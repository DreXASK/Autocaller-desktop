package serverScreen.data.repository.completedCallTasks

import core.domain.utils.ApiError
import core.domain.utils.Result
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import serverScreen.data.utils.ServerScreenHttpRoutes
import serverScreen.domain.repository.completedTasks.CompletedCallTaskTypes
import serverScreen.domain.repository.completedTasks.CompletedTaskDto
import serverScreen.domain.repository.completedTasks.CompletedTaskRepository

class CompletedTaskRepositoryRemote(private val httpClient: HttpClient): CompletedTaskRepository {

    override suspend fun getCompletedCallTaskList(parameter: CompletedCallTaskTypes.Parameter.Get): Result<List<CompletedTaskDto>, ApiError.CompletedCallTasksError> {
        return try {
            val response = httpClient.get {
                url(ServerScreenHttpRoutes.GET_COMPLETED_CALL_TASKS)
                setBody(parameter as CompletedCallTaskParameterGetRemote)
                contentType(ContentType.Application.Json)
            }
            when (response.status) {
                HttpStatusCode.OK -> Result.Success(response.body())
                else -> Result.Error(ApiError.CompletedCallTasksError.Remote.UnknownError(null))
            }

        } catch (e: Exception) {
            Result.Error(ApiError.CompletedCallTasksError.Remote.UnknownError(e))
        }
    }
}