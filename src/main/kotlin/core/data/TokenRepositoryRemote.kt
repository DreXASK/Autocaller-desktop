package core.data

import core.data.dto.TokenRequest
import core.domain.repository.TokenRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import serverScreen.data.remote.ServerScreenHttpRoutes
import core.data.dto.TokenResponse
import core.domain.ApiError
import core.domain.Error
import core.domain.Result
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import java.net.ConnectException


class TokenRepositoryRemote(private val httpClient: HttpClient) : TokenRepository {

    override suspend fun getToken(tokenRequest: TokenRequest): Result<TokenResponse, ApiError> {
        return try {
            val tokenResponse = httpClient.post {
                url(ServerScreenHttpRoutes.GET_TOKEN)
                contentType(ContentType.Application.Json)
                setBody(tokenRequest)
            }
            when (tokenResponse.status) {
                HttpStatusCode.OK  -> Result.Success(tokenResponse.body<TokenResponse>())
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