package unit.serverScreen.domain.usecase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

import core.data.tokens.RegisterResponseRemote
import core.domain.repository.RegisterRepository
import core.domain.usecase.RegisterOnServerUseCase
import kotlin.test.assertEquals

class RegisterOnServerUseCaseTest {

    private val registerRepository = mockk<RegisterRepository>()
    private val useCase = RegisterOnServerUseCase(registerRepository)

    @Test
    fun `return 'TEST' as a token`() = runTest {

        coEvery { registerRepository.getToken() } returns RegisterResponseRemote(token = "TEST")

        val actual = useCase.execute()
        val expected = "TEST"

        assertEquals(expected = expected, actual = actual)
    }
}