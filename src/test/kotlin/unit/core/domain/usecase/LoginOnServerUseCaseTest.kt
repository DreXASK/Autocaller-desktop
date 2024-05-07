package unit.core.domain.usecase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import core.data.login.LoginReceiveRemote
import core.data.login.LoginResponseRemote
import core.domain.TokenStatus
import core.domain.repository.LoginRepository
import core.domain.usecase.LoginOnServerUseCase
import kotlin.test.assertEquals

class LoginOnServerUseCaseTest {

    private val loginRepository = mockk<LoginRepository>()
    private val useCase = LoginOnServerUseCase(loginRepository)

    @Test
    fun `return connected status`() = runTest {

        coEvery { loginRepository.getTokenStatus(any()) } returns LoginResponseRemote(TokenStatus.REGISTERED)

        val actual = useCase.execute(LoginReceiveRemote(""))
        val expected = TokenStatus.REGISTERED

        assertEquals(expected = expected, actual = actual)
    }
}