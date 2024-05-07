package integration

import core.data.repository.login.LoginRepositoryRemote
import core.data.repository.login.ParameterRemote
import core.data.repository.login.ResponseRemote
import core.domain.utils.TokenStatus
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.mockk.spyk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LoginRepositoryRemoteTest {

    private val httpClient = spyk(HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    })
    private val connectionRepositoryRemote = LoginRepositoryRemote(httpClient)

    @Test
    fun `return unregistered when trying to connect to the server`() = runTest {

        val expected = ResponseRemote(TokenStatus.UNREGISTERED)

        val loginReceiveRemote = ParameterRemote("[E2E] Token")
        val actual = connectionRepositoryRemote.getTokenStatus(loginReceiveRemote)

        assertEquals(expected = expected, actual = actual)
    }
}