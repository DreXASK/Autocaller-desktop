package serverScreen.presentation


import core.domain.Result
import core.domain.ServerConnection
import core.domain.ServerConnectionStatus
import core.domain.usecase.DeleteServerConnectionSettingsUseCase
import core.domain.usecase.SaveServerConnectionSettingsUseCase
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent.inject
import serverScreen.domain.TasksTable
import serverScreen.presentation.components.serverControlPanel.ServerControlPanel

class ServerScreenViewModel {
    val serverControlPanel by inject<ServerControlPanel>(ServerControlPanel::class.java)
    val serverConnection by inject<ServerConnection>(ServerConnection::class.java)
    val tasksTable by inject<TasksTable>(TasksTable::class.java)
    private val saveServerConnectionSettingsUseCase by inject<SaveServerConnectionSettingsUseCase>(
        SaveServerConnectionSettingsUseCase::class.java
    )
    private val deleteServerConnectionSettingsUseCase by inject<DeleteServerConnectionSettingsUseCase>(
        DeleteServerConnectionSettingsUseCase::class.java
    )


    private var connectionJob: Job? = null
    fun connectToServer(ip: String, port: String) {
        connectionJob = CoroutineScope(Dispatchers.Default).launch {

            val result = serverConnection.registerOnServer(ip, port)

            if(result is Result.Success) {
                println(result.data.token)
                serverConnection.serverConnectionSettings?.let {
                    saveServerConnectionSettingsUseCase.execute(it)
                }
            } else {
                println("Could not connect to the server")
            }
        }
    }

    fun abortConnectionProcess() {
        connectionJob?.cancel()
        serverConnection.connectionStatus.value = ServerConnectionStatus.DISCONNECTED
    }

    fun logOutFromServer() {
        CoroutineScope(Dispatchers.Default).launch {
            serverConnection.logoutFromServer()
            deleteServerConnectionSettingsUseCase.execute()
        }
    }
}

