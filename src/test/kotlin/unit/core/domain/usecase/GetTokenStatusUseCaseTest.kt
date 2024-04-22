package unit.core.domain.usecase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import core.data.dto.TokenStatusRequest
import core.data.dto.TokenStatusResponse
import core.domain.TokenStatus
import core.domain.repository.TokenStatusRepository
import core.domain.usecase.GetTokenStatusUseCase
import kotlin.test.assertEquals

class GetTokenStatusUseCaseTest {

    private val tokenStatusRepository = mockk<TokenStatusRepository>()
    private val useCase = GetTokenStatusUseCase(tokenStatusRepository)

    @Test
    fun `return connected status`() = runTest {

        coEvery { tokenStatusRepository.getTokenStatus(any()) } returns
                TokenStatusResponse(TokenStatus.REGISTERED)

        val actual = useCase.execute(TokenStatusRequest(""))
        val expected = TokenStatus.REGISTERED

        assertEquals(expected = expected, actual = actual)
    }
}