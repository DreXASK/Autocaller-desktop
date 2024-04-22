package serverScreen.domain.usecase

import callScreen.domain.repository.ContactRepository
import callScreen.domain.usecase.GetContactListFromFileUseCase
import callScreen.mock.data.ContactRepositoryTest
import core.domain.ServerConnectionStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject
import org.koin.test.junit5.AutoCloseKoinTest
import serverScreen.data.remote.dto.ConnectionStatusRequest
import serverScreen.domain.mock.data.ConnectionRepositoryTest
import serverScreen.domain.repository.ConnectionRepository
import kotlin.test.assertEquals

class GetConnectionStatusUseCaseTest: AutoCloseKoinTest() {

    private val useCase by inject<GetConnectionStatusUseCase>(GetConnectionStatusUseCase::class.java)

    @Test
    fun `return connected status`() = runTest {

        startKoin {
            modules(
                module {
                    single { GetConnectionStatusUseCase(connectionRepository = get()) }
                    single<ConnectionRepository> { ConnectionRepositoryTest() }
                }
            )
        }

        val actual = useCase.execute(ConnectionStatusRequest(""))
        val expected = ServerConnectionStatus.Connected

        assertEquals(expected = expected, actual = actual)
    }
}