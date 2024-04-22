package serverScreen.data.remote

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import serverScreen.data.remote.dto.ConnectionStatusRequest
import serverScreen.data.remote.dto.ConnectionStatusResponse
import serverScreen.domain.repository.ConnectionRepository

class ConnectionRepositoryRemote(private val httpClient: HttpClient): ConnectionRepository {

    override suspend fun getConnectionStatus(tokenDTO: ConnectionStatusRequest): ConnectionStatusResponse {
        return try {
            httpClient.get {
                url(ServerScreenHttpRoutes.GET_CONNECTION_STATUS)
                setBody(tokenDTO.token)
            }.body<ConnectionStatusResponse>()
        } catch (e: ResponseException) {
            println(e.response.status.description)
            TODO()
        }
    }

}