package serverScreen.domain.repository

import serverScreen.data.remote.dto.ClientTokenStatusRequest
import io.ktor.client.statement.*

interface ClientTokenRepository {
    suspend fun getToken(): HttpResponse

    suspend fun getTokenStatus(tokenDTO: ClientTokenStatusRequest): HttpResponse

    //suspend fun sendPendingToken(pendingTokenRequest: PendingTokenRequest): HttpResponse

    //suspend fun createPost(connectionPermissionRequest: ConnectionPermissionRequest): HttpResponse
}