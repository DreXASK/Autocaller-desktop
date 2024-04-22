package unit.serverScreen.domain.usecase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

import core.data.dto.TokenResponse
import core.domain.repository.TokenRepository
import core.domain.usecase.GetTokenUseCase
import kotlin.test.assertEquals

class GetTokenUseCaseTest {

    private val tokenRepository = mockk<TokenRepository>()
    private val useCase = GetTokenUseCase(tokenRepository)

    @Test
    fun `return 'TEST' as a token`() = runTest {

        coEvery { tokenRepository.getToken() } returns TokenResponse(token = "TEST")

        val actual = useCase.execute()
        val expected = "TEST"

        assertEquals(expected = expected, actual = actual)
    }
}