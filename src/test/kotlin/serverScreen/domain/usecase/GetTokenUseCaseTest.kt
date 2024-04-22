package serverScreen.domain.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject
import org.koin.test.junit5.AutoCloseKoinTest
import serverScreen.data.remote.dto.TokenResponse
import serverScreen.domain.repository.ConnectionRepository
import serverScreen.domain.repository.TokenRepository
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