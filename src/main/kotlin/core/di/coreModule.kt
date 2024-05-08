package core.di

import core.data.repository.callTasks.CallTaskRepositoryRemote
import core.data.repository.serverConnectionSettings.ServerConnectionSettingsRepositoryLocal
import core.data.repository.login.LoginRepositoryRemote
import core.data.repository.messageTemplates.MessageTemplateRepositoryRemote
import core.domain.utils.ServerConnection
import core.domain.repository.ServerConnectionSettingsRepository
import core.domain.repository.callTasks.CallTaskRepository
import core.domain.repository.login.LoginRepository
import core.domain.repository.messageTemplates.MessageTemplateRepository
import core.domain.usecase.*
import core.domain.usecase.callTasks.GetCallTaskDataListUseCase
import core.domain.usecase.callTasks.SendCallTaskDtoListUseCase
import core.domain.usecase.messageTemplates.GetMessageTemplatesUseCase
import core.domain.usecase.messageTemplates.RemoveMessageTemplateUseCase
import core.domain.usecase.messageTemplates.SendMessageTemplateUseCase
import core.domain.usecase.serverConnectionSettings.DeleteServerConnectionSettingsUseCase
import core.domain.usecase.serverConnectionSettings.GetServerConnectionSettingsUseCase
import core.domain.usecase.serverConnectionSettings.SaveServerConnectionSettingsUseCase
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.dsl.module
import serverScreen.domain.MessageTemplateService

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
    single<LoginRepository> {
        LoginRepositoryRemote(httpClient = get())
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
    single<CallTaskRepository> {
        CallTaskRepositoryRemote(httpClient = get())
    }
    single {
        SendCallTaskDtoListUseCase(callTaskRepository = get())
    }
    single {
        GetCallTaskDataListUseCase(callTaskRepository = get())
    }
    single<MessageTemplateRepository> {
        MessageTemplateRepositoryRemote(httpClient = get())
    }
    single {
        GetMessageTemplatesUseCase(messageTemplateRepository = get())
    }
    single {
        RemoveMessageTemplateUseCase(messageTemplateRepository = get())
    }
    single {
        SendMessageTemplateUseCase(messageTemplateRepository = get())
    }
    single {
        MessageTemplateService()
    }
}