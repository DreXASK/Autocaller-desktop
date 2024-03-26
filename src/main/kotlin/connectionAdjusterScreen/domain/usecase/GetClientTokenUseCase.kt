package connectionAdjusterScreen.domain.usecase

import connectionAdjusterScreen.data.remote.dto.ClientTokenRequest
import connectionAdjusterScreen.data.remote.dto.ClientTokenResponse
import connectionAdjusterScreen.domain.repository.ClientTokenRepository
import io.ktor.client.call.*
import org.koin.java.KoinJavaComponent.inject

class GetClientTokenUseCase {
    private val clientTokenRepository by inject<ClientTokenRepository>(ClientTokenRepository::class.java)
    suspend fun execute(): String {
        return clientTokenRepository.getToken().body<ClientTokenResponse>().token
    }
}