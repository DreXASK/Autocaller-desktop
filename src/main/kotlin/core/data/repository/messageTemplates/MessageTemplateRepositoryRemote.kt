package core.data.repository.messageTemplates

import core.data.utils.CoreHttpRoutes
import core.domain.models.MessageTemplateData
import core.domain.repository.messageTemplates.MessageTemplateRepository
import core.domain.repository.messageTemplates.MessageTemplateTypes
import core.domain.utils.ApiError
import core.domain.utils.Result
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class MessageTemplateRepositoryRemote(private val httpClient: HttpClient): MessageTemplateRepository {

    override suspend fun getMessageTemplateList(parameter: MessageTemplateTypes.Parameter.Get): Result<List<MessageTemplateData>, ApiError.MessageTemplatesError> {
        return try {
            val response = httpClient.get {
                url(CoreHttpRoutes.getMessageTemplates)
                setBody(parameter as MessageTemplateParameterGetRemote)
                contentType(ContentType.Application.Json)
            }
            when (response.status) {
                HttpStatusCode.OK -> Result.Success(response.body())
                else -> Result.Error(ApiError.MessageTemplatesError.Remote.UnknownError(response.body()))
            }

        } catch (e: Exception) {
            Result.Error(ApiError.MessageTemplatesError.Remote.UnknownError(e.message.toString()))
        }
    }

    override suspend fun removeMessageTemplate(parameter: MessageTemplateTypes.Parameter.Remove): Result<Unit, ApiError.MessageTemplatesError> {
        return try {
            val response = httpClient.post {
                url(CoreHttpRoutes.removeMessageTemplate)
                setBody(parameter as MessageTemplateParameterRemoveRemote)
                contentType(ContentType.Application.Json)
            }
            when (response.status) {
                HttpStatusCode.OK -> Result.Success(Unit)
                else -> Result.Error(ApiError.MessageTemplatesError.Remote.UnknownError(response.body()))
            }

        } catch (e: Exception) {
            Result.Error(ApiError.MessageTemplatesError.Remote.UnknownError(e.message.toString()))
        }
    }

    override suspend fun sendMessageTemplate(parameter: MessageTemplateTypes.Parameter.Send): Result<Unit, ApiError.MessageTemplatesError> {
        return try {
            val response = httpClient.post {
                url(CoreHttpRoutes.sendMessageTemplate)
                setBody(parameter as MessageTemplateParameterSendRemote)
                contentType(ContentType.Application.Json)
            }
            when (response.status) {
                HttpStatusCode.OK -> Result.Success(Unit)
                else -> Result.Error(ApiError.MessageTemplatesError.Remote.UnknownError(response.body()))
            }

        } catch (e: Exception) {
            Result.Error(ApiError.MessageTemplatesError.Remote.UnknownError(e.message.toString()))
        }
    }

}