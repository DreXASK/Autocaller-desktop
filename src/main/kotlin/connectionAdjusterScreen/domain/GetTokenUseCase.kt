package connectionAdjusterScreen.domain

import connectionAdjusterScreen.data.remote.dto.TokenRequest
import connectionAdjusterScreen.domain.repository.TokensRepository
import io.ktor.client.call.*
import org.koin.java.KoinJavaComponent

class GetTokenUseCase {
    private val tokensRepository by KoinJavaComponent.inject<TokensRepository>(TokensRepository::class.java)
    suspend fun execute(): String {
        return tokensRepository.getToken().body<TokenRequest>().token
    }
}