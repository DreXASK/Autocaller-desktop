package core.di

import core.data.ServerConnectionSettingsRepositoryLocal
import core.data.TokenRepositoryRemote
import core.data.TokenStatusRepositoryRemote
import core.domain.ServerConnection
import core.domain.repository.ServerConnectionSettingsRepository
import core.domain.repository.TokenRepository
import core.domain.repository.TokenStatusRepository
import core.domain.usecase.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.dsl.module

val coreModule = module {
    single {
        ServerConnection()
    }
    single {
        HttpClient(CIO) {
            //    install(Logging) { level = LogLevel.ALL }
            install(ContentNegotiation) {
                json()
            }
        }
    }
    single<TokenRepository> {
        TokenRepositoryRemote(httpClient = get())
    }
    single<TokenStatusRepository> {
        TokenStatusRepositoryRemote(httpClient = get())
    }
    single {
        GetTokenUseCase(repository = get())
    }
    single {
        GetTokenStatusUseCase(repository = get())
    }
    single {
        GetServerConnectionSettingsUseCase(repository = get())
    }
    single {
        SaveServerConnectionSettingsUseCase(repository = get())
    }
    single {
        DeleteServerConnectionSettingsUseCase(repository = get())
    }
    single<ServerConnectionSettingsRepository> {
        ServerConnectionSettingsRepositoryLocal()
    }
}