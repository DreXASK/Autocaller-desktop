package connectionAdjusterScreen.di

import connectionAdjusterScreen.domain.repository.TokensRepository
import connectionAdjusterScreen.data.remote.TokensRepositoryRemote
import connectionAdjusterScreen.domain.CheckConnectionStatusUseCase
import connectionAdjusterScreen.domain.GetTokenUseCase
import connectionAdjusterScreen.presentation.ConnectionAdjusterScreenViewModel
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.dsl.module

val connectionAdjusterScreenModule = module {

    single {
            HttpClient(CIO) {
                //    install(Logging) { level = LogLevel.ALL }
                install(ContentNegotiation) {
                    json()
                }
            }
    }

    single {
        ConnectionAdjusterScreenViewModel()
    }
    single<TokensRepository> {
        TokensRepositoryRemote(client = get())
    }
    single {
        GetTokenUseCase()
    }
    single {
        CheckConnectionStatusUseCase(client = get())
    }
}