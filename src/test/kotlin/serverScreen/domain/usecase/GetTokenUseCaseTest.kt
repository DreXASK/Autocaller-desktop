package serverScreen.domain.usecase

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject
import org.koin.test.junit5.AutoCloseKoinTest
import serverScreen.domain.mock.data.ConnectionRepositoryTest
import serverScreen.domain.mock.data.TokenRepositoryTest
import serverScreen.domain.repository.ConnectionRepository
import serverScreen.domain.repository.TokenRepository
import kotlin.test.assertEquals

class GetTokenUseCaseTest: AutoCloseKoinTest() {

    private val useCase by inject<GetTokenUseCase>(GetTokenUseCase::class.java)

    @Test
    fun `return 'TEST' as a token`() = runTest {

        startKoin {
            modules(
                module {
                    single { GetTokenUseCase(tokenRepository = get()) }
                    single<TokenRepository> { TokenRepositoryTest() }
                }
            )
        }

        val actual = useCase.execute()
        val expected = "TEST"

        assertEquals(expected = expected, actual = actual)
    }
}