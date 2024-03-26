package connectionAdjusterScreen.domain.repository

import connectionAdjusterScreen.data.remote.dto.ClientTokenRequest
import connectionAdjusterScreen.data.remote.dto.ClientTokenResponse
import connectionAdjusterScreen.data.remote.dto.ClientTokenStatusRequest
import io.ktor.client.statement.*

interface ClientTokenRepository {
    suspend fun getToken(): HttpResponse

    suspend fun getTokenStatus(tokenDTO: ClientTokenStatusRequest): HttpResponse

    //suspend fun sendPendingToken(pendingTokenRequest: PendingTokenRequest): HttpResponse

    //suspend fun createPost(connectionPermissionRequest: ConnectionPermissionRequest): HttpResponse
}