package core.data.tokens

import core.domain.repository.RegisterRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import serverScreen.data.remote.ServerScreenHttpRoutes
import core.domain.ApiError
import core.domain.Result
import io.ktor.http.*
import kotlinx.serialization.json.Json
import java.net.ConnectException


class RegisterRepositoryRemote(private val httpClient: HttpClient) : RegisterRepository {

    override suspend fun getToken(registerReceiveRemote: RegisterReceiveRemote): Result<RegisterResponseRemote, ApiError> {
        return try {
            val tokenResponse = httpClient.post {
                url(ServerScreenHttpRoutes.GET_TOKEN)
                contentType(ContentType.Application.Json)
                setBody(registerReceiveRemote)
            }
            when (tokenResponse.status) {
                HttpStatusCode.OK  -> Result.Success(tokenResponse.body<RegisterResponseRemote>())
                HttpStatusCode.BadRequest -> {
                    val error = Json.decodeFromString<ApiError.TokenError>(tokenResponse.body<String>())
                    when(error) {
                        ApiError.TokenError.INVALID_CONNECTION_KEY -> Result.Error(ApiError.TokenError.INVALID_CONNECTION_KEY)
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