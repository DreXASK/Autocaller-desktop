package serverScreen.domain.usecase

import serverScreen.data.remote.dto.ClientTokenResponse
import serverScreen.domain.repository.ClientTokenRepository
import io.ktor.client.call.*
import org.koin.java.KoinJavaComponent.inject

class GetClientTokenUseCase {
    private val clientTokenRepository by inject<ClientTokenRepository>(ClientTokenRepository::class.java)
    suspend fun execute(): String {
        return clientTokenRepository.getToken().body<ClientTokenResponse>().token
    }
}