package unit.core.domain.usecase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import core.data.repository.login.LoginParameterRemote
import core.data.repository.login.LoginResponseRemote
import core.domain.utils.TokenStatus
import core.domain.repository.login.LoginRepository
import core.domain.usecase.LoginOnServerUseCase
import kotlin.test.assertEquals

class LoginOnServerUseCaseTest {

    private val loginRepository = mockk<LoginRepository>()
    private val useCase = LoginOnServerUseCase(loginRepository)

    @Test
    fun `return connected status`() = runTest {

        coEvery { loginRepository.getTokenStatus(any()) } returns LoginResponseRemote(TokenStatus.REGISTERED)

        val actual = useCase.execute(LoginParameterRemote(""))
        val expected = TokenStatus.REGISTERED

        assertEquals(expected = expected, actual = actual)
    }
}