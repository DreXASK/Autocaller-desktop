package core.data.login

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import core.domain.ApiError
import core.domain.Result
import core.domain.repository.LoginRepository
import io.ktor.http.*
import kotlinx.serialization.json.Json
import serverScreen.data.remote.ServerScreenHttpRoutes
import java.net.ConnectException

class LoginRepositoryRemote(private val httpClient: HttpClient): LoginRepository {

    override suspend fun getTokenStatus(loginReceiveRemote: LoginReceiveRemote): Result<LoginResponseRemote, ApiError> {
        return try {
            val tokenStatusResponse = httpClient.get {
                url(ServerScreenHttpRoutes.GET_TOKEN_STATUS)
                setBody(loginReceiveRemote)
                contentType(ContentType.Application.Json)
            }
            when (tokenStatusResponse.status) {
                HttpStatusCode.OK  -> Result.Success(tokenStatusResponse.body<LoginResponseRemote>())
                HttpStatusCode.BadRequest -> {
                    val error = Json.decodeFromString<ApiError.TokenStatusError>(tokenStatusResponse.body<String>())
                    when(error) {
                        ApiError.TokenStatusError.INVALID_TOKEN -> Result.Error(ApiError.TokenStatusError.INVALID_TOKEN)
                    }
                }
                else -> Result.Error(ApiError.UnknownError)
            }
        } catch (e: HttpRequestTimeoutException) {
            Result.Error(ApiError.Network.REQUEST_TIMEOUT)
        } catch (e: ConnectException) {
            Result.Error(ApiError.Network.CONNECTION_REFUSED)
        }
    }

}