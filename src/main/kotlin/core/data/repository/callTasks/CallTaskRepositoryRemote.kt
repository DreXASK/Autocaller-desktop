package core.data.repository.callTasks

import core.data.utils.CoreHttpRoutes
import core.domain.utils.ApiError
import core.domain.utils.Result
import core.domain.models.CallTaskData
import core.domain.repository.callTasks.CallTasksDto
import core.domain.repository.callTasks.CallTaskRepository
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class CallTaskRepositoryRemote(private val httpClient: HttpClient) : CallTaskRepository {

    override suspend fun getContactList(parameter: CallTasksDto.Parameter.Get): Result<List<CallTaskData>, ApiError.ContactsError> {
        TODO()
    }

    override suspend fun sendContactList(parameter: CallTasksDto.Parameter.Send): Result<Unit, ApiError.ContactsError> {
        return try {
            val contactsResponse = httpClient.post {
                url(CoreHttpRoutes.SEND_TASKS)
                setBody(parameter as CallTasksParameterSendRemote)
                contentType(ContentType.Application.Json)
            }
            when (contactsResponse.status) {
                HttpStatusCode.OK -> Result.Success(Unit)
                else -> Result.Error(ApiError.ContactsError.Remote.UnknownError(null))
            }

        } catch (e: Exception) {
            Result.Error(ApiError.ContactsError.Remote.UnknownError(e))
        }
    }

}