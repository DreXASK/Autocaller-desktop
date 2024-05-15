package serverScreen.presentation


import core.data.repository.callTasks.CallTaskParameterGetRemote
import core.data.repository.callTasks.CallTaskParameterRemoveRemote
import core.data.repository.messageTemplates.MessageTemplateParameterGetRemote
import core.data.repository.messageTemplates.MessageTemplateParameterRemoveRemote
import core.data.repository.messageTemplates.MessageTemplateParameterSendRemote
import core.domain.models.CallTaskData
import core.domain.models.MessageTemplateData
import core.domain.utils.Result
import core.domain.ServerConnection
import core.domain.utils.ServerConnectionStatus
import core.domain.usecase.serverConnectionSettings.DeleteServerConnectionSettingsUseCase
import core.domain.usecase.callTasks.GetCallTaskDataListUseCase
import core.domain.usecase.serverConnectionSettings.SaveServerConnectionSettingsUseCase
import core.domain.utils.ApiError
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent.get
import org.koin.java.KoinJavaComponent.inject
import serverScreen.data.repository.callProcessSettings.CallProcessSettingsParameterGetRemote
import serverScreen.data.repository.callProcessSettings.CallProcessSettingsParameterSendRemote
import serverScreen.data.repository.completedCallTasks.CompletedCallTaskParameterGetRemote
import serverScreen.domain.CallProcessSettingsService
import serverScreen.domain.CompletedTasksTable
import serverScreen.domain.CallTasksTable
import core.domain.MessageTemplateService
import serverScreen.domain.models.CallProcessSettingsData
import serverScreen.domain.models.CompletedTaskData
import serverScreen.domain.usecase.GetCompletedTaskDataListUseCase
import serverScreen.domain.usecase.RemoveCallTaskUseCase
import serverScreen.presentation.components.serverControlPanel.ServerControlPanel
import serverScreen.presentation.utils.transformToCallProcessSettingsDto

class ServerScreenViewModel {
    val serverControlPanel by inject<ServerControlPanel>(ServerControlPanel::class.java)
    val serverConnection by inject<ServerConnection>(ServerConnection::class.java)
    val callTasksTable by inject<CallTasksTable>(CallTasksTable::class.java)
    val completedTasksTable by inject<CompletedTasksTable>(CompletedTasksTable::class.java)
    val messageTemplateService by inject<MessageTemplateService>(MessageTemplateService::class.java)
    val callProcessSettingsService by inject<CallProcessSettingsService>(CallProcessSettingsService::class.java)

    private var connectionJob: Job? = null

    fun connectToServer(ip: String, port: String, token: String) {
        connectionJob = CoroutineScope(Dispatchers.Default).launch {
            val saveServerConnectionSettingsUseCase =
                get<SaveServerConnectionSettingsUseCase>(SaveServerConnectionSettingsUseCase::class.java)

            val result = serverConnection.loginOnServer(ip, port, token)

            if (result is Result.Success) {
                serverConnection.serverConnectionSettings?.let {
                    saveServerConnectionSettingsUseCase.execute(it)
                    println("ServerConnectionSettings saved to the file storage")
                }
            }
        }
    }

    fun abortConnectionProcess() {
        connectionJob?.cancel()
        serverConnection.connectionStatus.value = ServerConnectionStatus.DISCONNECTED
    }

    fun logOutFromServer() {
        CoroutineScope(Dispatchers.Default).launch {
            val deleteServerConnectionSettingsUseCase =
                get<DeleteServerConnectionSettingsUseCase>(DeleteServerConnectionSettingsUseCase::class.java)

            serverConnection.logoutFromServer()
            if (deleteServerConnectionSettingsUseCase.execute() is Result.Success)
                println("ServerConnectionSettings removed from the file storage")
        }
    }

    suspend fun getCallTaskListFromServer(): List<CallTaskData>? {
        val getCallTaskDataListUseCase = get<GetCallTaskDataListUseCase>(GetCallTaskDataListUseCase::class.java)
        val callTaskDtoParameter =
            serverConnection.serverConnectionSettings?.let { CallTaskParameterGetRemote(it.token) }

        if (callTaskDtoParameter == null) {
            println("getCallTaskListFromServer error - serverConnectionSettings is null")
            return null
        }

        when (val result = getCallTaskDataListUseCase.execute(callTaskDtoParameter)) {
            is Result.Success -> {
                return result.data
            }

            is Result.Error -> {
                when (result.error) {
                    is ApiError.CallTasksError.Remote.UnknownError -> {
                        println("getCallTaskListFromServer error - ${result.error}")
                        return null
                    }
                }
            }
        }
    }

    suspend fun removeCallTaskFromServer(id: Long): Boolean {
        val useCase = get<RemoveCallTaskUseCase>(RemoveCallTaskUseCase::class.java)
        val callTaskDtoParameter =
            serverConnection.serverConnectionSettings?.let { CallTaskParameterRemoveRemote(it.token, id) }

        if (callTaskDtoParameter == null) {
            println("removeCallTaskFromServer error - serverConnectionSettings is null")
            return false
        }

        return when (val result = useCase.execute(callTaskDtoParameter)) {
            is Result.Success -> {
                println("CallTask with id = $id was successfully removed from the server")
                true
            }
            is Result.Error -> {
                when (result.error) {
                    is ApiError.CallTasksError.Remote.UnknownError -> {
                        println("removeCallTaskFromServer error - ${result.error}")
                        false
                    }
                }
            }
        }
    }


    suspend fun getCompletedTaskListFromServer(): List<CompletedTaskData>? {
        val getCompletedTaskDataListUseCase =
            get<GetCompletedTaskDataListUseCase>(GetCompletedTaskDataListUseCase::class.java)
        val completedTaskDtoParameter =
            serverConnection.serverConnectionSettings?.let { CompletedCallTaskParameterGetRemote(it.token) }

        if (completedTaskDtoParameter == null) {
            println("getCompletedTaskListFromServer error - serverConnectionSettings is null")
            return null
        }

        return when (val result = getCompletedTaskDataListUseCase.execute(completedTaskDtoParameter)) {
            is Result.Success -> {
                result.data
            }

            is Result.Error -> {
                when (result.error) {
                    is ApiError.CompletedTasksError.Remote.UnknownError -> {
                        println("getCompletedTaskListFromServer error - ${result.error}")
                        null
                    }
                }
            }
        }
    }

    suspend fun getMessageTemplatesFromServer(): List<MessageTemplateData>? {
        val messageTemplateDtoParameter =
            serverConnection.serverConnectionSettings?.let { MessageTemplateParameterGetRemote(it.token) }

        if (messageTemplateDtoParameter == null) {
            println("getMessageTemplatesFromServer error - serverConnectionSettings is null")
            return null
        }

        return when (val result = messageTemplateService.getMessageTemplatesFromServer(messageTemplateDtoParameter)) {
            is Result.Success -> {
                result.data
            }
            is Result.Error -> {
                when (result.error) {
                    is ApiError.MessageTemplatesError.Remote.UnknownError -> {
                        println("getMessageTemplatesFromServer error - ${result.error}")
                        null
                    }
                }
            }
        }
    }

    suspend fun sendMessageTemplateToServer(messageTemplate: MessageTemplateData): Boolean {
        val messageTemplateDtoParameter =
            serverConnection.serverConnectionSettings?.let {
                MessageTemplateParameterSendRemote(
                    it.token,
                    messageTemplate
                )
            }

        if (messageTemplateDtoParameter == null) {
            println("sendMessageTemplateToServer error - serverConnectionSettings is null")
            return false
        }

        return when (val result = messageTemplateService.sendMessageTemplateToServer(messageTemplateDtoParameter)) {
            is Result.Success -> {
                println("MessageTemplate was successfully sent to the server")
                true
            }
            is Result.Error -> {
                when (result.error) {
                    is ApiError.MessageTemplatesError.Remote.UnknownError -> {
                        println("sendMessageTemplateToServer error - ${result.error}")
                        false
                    }
                }
            }
        }
    }

    suspend fun removeMessageTemplateFromServer(id: Long): Boolean {
        val messageTemplateDtoParameter =
            serverConnection.serverConnectionSettings?.let { MessageTemplateParameterRemoveRemote(it.token, id) }

        if (messageTemplateDtoParameter == null) {
            println("removeMessageTemplateFromServer error - serverConnectionSettings is null")
            return false
        }

        return when (val result = messageTemplateService.removeMessageTemplateFromServer(messageTemplateDtoParameter)) {
            is Result.Success -> {
                messageTemplateService.removeMessageTemplateById(id)
                println("MessageTemplate was successfully removed from the server")
                true
            }

            is Result.Error -> {
                when (result.error) {
                    is ApiError.MessageTemplatesError.Remote.UnknownError -> {
                        println("removeMessageTemplateFromServer error - ${result.error}")
                        false
                    }
                }
            }
        }
    }

    suspend fun getCallProcessSettingsFromServer(): CallProcessSettingsData? {
        val callProcessSettingsParameter =
            serverConnection.serverConnectionSettings?.let { CallProcessSettingsParameterGetRemote(it.token) }

        if (callProcessSettingsParameter == null) {
            println("getCallProcessSettingsFromServer error - serverConnectionSettings is null")
            return null
        }

        return when (val result = callProcessSettingsService.getSettingsFromServer(callProcessSettingsParameter)) {
            is Result.Success -> {
                result.data
            }
            is Result.Error -> {
                when (result.error) {
                    is ApiError.CallProcessSettings.Remote.UnknownError -> {
                        println("getCallProcessSettingsFromServer error - ${result.error}")
                        null
                    }
                }
            }
        }
    }

    suspend fun sendCallProcessSettingsToServer(): Boolean {

        val settingsData = callProcessSettingsService.getSettingsData()
            if(settingsData == null) {
                println("sendCallProcessSettingsToServer  error - CallProcessSettingsData is null")
                return false
            }

        val settingsDto = settingsData.transformToCallProcessSettingsDto()

        val callProcessSettingsParameter =
            serverConnection.serverConnectionSettings?.let { CallProcessSettingsParameterSendRemote(it.token, settingsDto) }

        if (callProcessSettingsParameter == null) {
            println("${javaClass.enclosingMethod.name}  error - serverConnectionSettings is null")
            return false
        }

        return when (val result = callProcessSettingsService.sendSettingsToServer(callProcessSettingsParameter)) {
            is Result.Success -> {
                true
            }
            is Result.Error -> {
                when (result.error) {
                    is ApiError.CallProcessSettings.Remote.UnknownError -> {
                        println("sendCallProcessSettingsToServer error - ${result.error}")
                        false
                    }
                }
            }
        }
    }

}

