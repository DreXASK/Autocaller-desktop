package core.data.repository.callTasks

import core.data.utils.CoreHttpRoutes
import core.domain.repository.callTasks.CallTaskDto
import core.domain.utils.ApiError
import core.domain.utils.Result
import core.domain.repository.callTasks.CallTaskTypes
import core.domain.repository.callTasks.CallTaskRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class CallTaskRepositoryRemote(private val httpClient: HttpClient): CallTaskRepository {

    override suspend fun getCallTaskList(parameter: CallTaskTypes.Parameter.Get): Result<List<CallTaskDto>, ApiError.CallTasksError> {
        return try {
            val response = httpClient.get {
                url(CoreHttpRoutes.GET_CALL_TASKS)
                setBody(parameter as CallTaskParameterGetRemote)
                contentType(ContentType.Application.Json)
            }
            when (response.status) {
                HttpStatusCode.OK -> Result.Success(response.body())
                else -> Result.Error(ApiError.CallTasksError.Remote.UnknownError(null))
            }

        } catch (e: Exception) {
            Result.Error(ApiError.CallTasksError.Remote.UnknownError(e))
        }
    }

    override suspend fun removeCallTask(parameter: CallTaskTypes.Parameter.Remove): Result<Unit, ApiError.CallTasksError> {
        return try {
            val contactsResponse = httpClient.post {
                url(CoreHttpRoutes.REMOVE_CALL_TASK)
                setBody(parameter as CallTaskParameterRemoveRemote)
                contentType(ContentType.Application.Json)
            }
            when (contactsResponse.status) {
                HttpStatusCode.OK -> Result.Success(Unit)
                else -> Result.Error(ApiError.CallTasksError.Remote.UnknownError(null))
            }

        } catch (e: Exception) {
            Result.Error(ApiError.CallTasksError.Remote.UnknownError(e))
        }
    }

    override suspend fun sendCallTaskList(parameter: CallTaskTypes.Parameter.Send): Result<Unit, ApiError.CallTasksError> {
        return try {
            val contactsResponse = httpClient.post {
                url(CoreHttpRoutes.SEND_CALL_TASKS)
                setBody(parameter as CallTaskParameterSendRemote)
                contentType(ContentType.Application.Json)
            }
            when (contactsResponse.status) {
                HttpStatusCode.OK -> Result.Success(Unit)
                else -> Result.Error(ApiError.CallTasksError.Remote.UnknownError(null))
            }

        } catch (e: Exception) {
            Result.Error(ApiError.CallTasksError.Remote.UnknownError(e))
        }
    }

}