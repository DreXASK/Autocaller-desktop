package serverScreen.data.repository.callProcessSettings

import core.domain.utils.ApiError
import core.domain.utils.Result
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import serverScreen.data.utils.ServerScreenHttpRoutes
import serverScreen.domain.repository.callProcessSettings.CallProcessSettingsDto
import serverScreen.domain.repository.callProcessSettings.CallProcessSettingsRepository
import serverScreen.domain.repository.callProcessSettings.CallProcessSettingsTypes

    class CallProcessSettingsRepositoryRemote(private val httpClient: HttpClient): CallProcessSettingsRepository {

    override suspend fun getCallProcessSettings(parameter: CallProcessSettingsTypes.Parameter.Get): Result<CallProcessSettingsDto, ApiError.CallProcessSettings> {
        return try {
            val response = httpClient.get {
                url(ServerScreenHttpRoutes.getCallProcessSettings)
                setBody(parameter as CallProcessSettingsParameterGetRemote)
                contentType(ContentType.Application.Json)
            }
            when (response.status) {
                HttpStatusCode.OK -> Result.Success(response.body())
                else -> Result.Error(ApiError.CallProcessSettings.Remote.UnknownError(response.body()))
            }

        } catch (e: Exception) {
            Result.Error(ApiError.CallProcessSettings.Remote.UnknownError(e.message.toString()))
        }
    }

    override suspend fun sendCallProcessSettings(parameter: CallProcessSettingsTypes.Parameter.Send): Result<Unit, ApiError.CallProcessSettings> {
        return try {
            val response = httpClient.post {
                url(ServerScreenHttpRoutes.sendCallProcessSettings)
                setBody(parameter as CallProcessSettingsParameterSendRemote)
                contentType(ContentType.Application.Json)
            }
            when (response.status) {
                HttpStatusCode.OK -> Result.Success(Unit)
                else -> Result.Error(ApiError.CallProcessSettings.Remote.UnknownError(response.body()))
            }
        } catch (e: Exception) {
            Result.Error(ApiError.CallProcessSettings.Remote.UnknownError(e.message.toString()))
        }
    }
}