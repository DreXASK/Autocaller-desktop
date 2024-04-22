package core.data

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import core.data.dto.TokenStatusRequest
import core.data.dto.TokenStatusResponse
import core.domain.repository.TokenStatusRepository
import io.ktor.http.*
import serverScreen.data.remote.ServerScreenHttpRoutes

class TokenStatusRepositoryRemote(private val httpClient: HttpClient): TokenStatusRepository {

    override suspend fun getTokenStatus(tokenStatusRequest: TokenStatusRequest): TokenStatusResponse {
        return try {
            httpClient.get {
                url(ServerScreenHttpRoutes.GET_TOKEN_STATUS)
                setBody(tokenStatusRequest)
                contentType(ContentType.Application.Json)
            }.body<TokenStatusResponse>()
        } catch (e: ResponseException) {
            println(e.response.status.description)
            TODO()
        }
    }

}