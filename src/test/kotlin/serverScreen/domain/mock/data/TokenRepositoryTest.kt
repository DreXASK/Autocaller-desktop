package serverScreen.domain.mock.data

import io.ktor.client.statement.*
import serverScreen.data.remote.dto.ConnectionStatusRequest
import serverScreen.domain.repository.TokenRepository

class TokenRepositoryTest: TokenRepository {
    override suspend fun getToken(): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getTokenStatus(tokenDTO: ConnectionStatusRequest): HttpResponse {
        TODO("Not yet implemented")
    }
}