package connectionAdjusterScreen.domain.repository

import io.ktor.client.statement.*

interface TokensRepository {
    suspend fun getToken(): HttpResponse

    //suspend fun sendPendingToken(pendingTokenRequest: PendingTokenRequest): HttpResponse

    //suspend fun createPost(connectionPermissionRequest: ConnectionPermissionRequest): HttpResponse
}