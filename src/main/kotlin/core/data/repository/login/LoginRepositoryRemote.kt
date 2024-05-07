package core.data.repository.login

import core.data.utils.CoreHttpRoutes
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import core.domain.utils.ApiError
import core.domain.utils.Result
import core.domain.utils.TokenStatus
import core.domain.repository.login.LoginDto
import core.domain.repository.login.LoginRepository
import io.ktor.http.*
import kotlinx.serialization.json.Json
import java.net.ConnectException

class LoginRepositoryRemote(private val httpClient: HttpClient): LoginRepository {

    override suspend fun getTokenStatus(parameter: LoginDto.Parameter): Result<TokenStatus, ApiError> {
        return try {
            val tokenStatusResponse = httpClient.get {
                url(CoreHttpRoutes.GET_TOKEN_STATUS)
                setBody(parameter as ParameterRemote)
                contentType(ContentType.Application.Json)
            }
            when (tokenStatusResponse.status) {
                HttpStatusCode.OK  -> Result.Success(tokenStatusResponse.body<ResponseRemote>().tokenStatus)
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