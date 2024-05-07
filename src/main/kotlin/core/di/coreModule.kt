package core.di

import core.data.serverConnectionSettings.ServerConnectionSettingsRepositoryLocal
import core.data.tokens.RegisterRepositoryRemote
import core.data.login.LoginRepositoryRemote
import core.domain.ServerConnection
import core.domain.repository.ServerConnectionSettingsRepository
import core.domain.repository.RegisterRepository
import core.domain.repository.LoginRepository
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
    single<RegisterRepository> {
        RegisterRepositoryRemote(httpClient = get())
    }
    single<LoginRepository> {
        LoginRepositoryRemote(httpClient = get())
    }
    single {
        RegisterOnServerUseCase(repository = get())
    }
    single {
        LoginOnServerUseCase(repository = get())
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