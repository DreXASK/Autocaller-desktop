package core.domain.usecase

import core.domain.repository.TokenRepository

class GetTokenUseCase(private val tokenRepository: TokenRepository) {

    suspend fun execute(): String {
        return tokenRepository.getToken().token
    }

}