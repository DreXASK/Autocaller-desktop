package e2e

import core.data.TokenStatusRepositoryRemote
import core.data.dto.TokenStatusRequest
import core.data.dto.TokenStatusResponse
import core.domain.TokenStatus
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.mockk.spyk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TokenStatusRepositoryRemoteTest {

    private val httpClient = spyk(HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    })
    private val connectionRepositoryRemote = TokenStatusRepositoryRemote(httpClient)

    @Test
    fun `abc abc`() = runTest {

        val expected = TokenStatusResponse(TokenStatus.UNREGISTERED)

        val tokenStatusRequest = TokenStatusRequest("[E2E] Token")
        val actual = connectionRepositoryRemote.getTokenStatus(tokenStatusRequest)

        assertEquals(expected = expected, actual = actual)
    }
}