package integration

import core.data.login.LoginRepositoryRemote
import core.data.login.LoginReceiveRemote
import core.data.login.LoginResponseRemote
import core.domain.TokenStatus
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

        val expected = LoginResponseRemote(TokenStatus.UNREGISTERED)

        val loginReceiveRemote = LoginReceiveRemote("[E2E] Token")
        val actual = connectionRepositoryRemote.getTokenStatus(loginReceiveRemote)

        assertEquals(expected = expected, actual = actual)
    }
}