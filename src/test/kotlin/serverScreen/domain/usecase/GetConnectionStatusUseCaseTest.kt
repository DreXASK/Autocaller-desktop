package serverScreen.domain.usecase

import core.domain.ServerConnectionStatus
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import serverScreen.data.remote.dto.ConnectionStatusRequest
import serverScreen.data.remote.dto.ConnectionStatusResponse
import serverScreen.domain.repository.ConnectionRepository
import kotlin.test.assertEquals

class GetConnectionStatusUseCaseTest {

    private val connectionRepository = mockk<ConnectionRepository>()
    private val useCase = GetConnectionStatusUseCase(connectionRepository)

    @Test
    fun `return connected status`() = runTest {

        coEvery { connectionRepository.getConnectionStatus(any()) } returns
                ConnectionStatusResponse(ServerConnectionStatus.CONNECTED)

        val actual = useCase.execute(ConnectionStatusRequest(""))
        val expected = ServerConnectionStatus.CONNECTED

        assertEquals(expected = expected, actual = actual)
    }
}