package serverScreen.domain.usecase

import serverScreen.data.remote.dto.TokenResponse
import serverScreen.domain.repository.TokenRepository
import io.ktor.client.call.*

class GetTokenUseCase(private val tokenRepository: TokenRepository) {

    suspend fun execute(): String {
        return tokenRepository.getToken().token
    }

}