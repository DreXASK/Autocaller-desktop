package core.data.repository.callTasks

import core.data.utils.CoreHttpRoutes
import core.domain.utils.ApiError
import core.domain.utils.Result
import core.domain.repository.callTasks.CallTasksTypes
import core.domain.repository.callTasks.CallTaskRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CallTaskRepositoryRemote(private val httpClient: HttpClient) : CallTaskRepository {

    override suspend fun getCallTaskList(parameter: CallTasksTypes.Parameter.Get): Result<List<CallTaskDto>, ApiError.CallTasksError> {
        return try {
            val contactsResponse = httpClient.get {
                url(CoreHttpRoutes.GET_TASKS)
                setBody(parameter as CallTasksParameterGetRemote)
                contentType(ContentType.Application.Json)
            }
            when (contactsResponse.status) {
                HttpStatusCode.OK -> Result.Success(contactsResponse.body())
                else -> Result.Error(ApiError.CallTasksError.Remote.UnknownError(null))
            }

        } catch (e: Exception) {
            Result.Error(ApiError.CallTasksError.Remote.UnknownError(e))
        }
    }

    override suspend fun removeCallTask(parameter: CallTasksTypes.Parameter.Remove): Result<Unit, ApiError.CallTasksError> {
        return try {
            val contactsResponse = httpClient.post {
                url(CoreHttpRoutes.REMOVE_TASKS)
                setBody(parameter as CallTasksParameterRemoveRemote)
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

    override suspend fun sendCallTaskList(parameter: CallTasksTypes.Parameter.Send): Result<Unit, ApiError.CallTasksError> {
        return try {
            val contactsResponse = httpClient.post {
                url(CoreHttpRoutes.SEND_TASKS)
                setBody(parameter as CallTasksParameterSendRemote)
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