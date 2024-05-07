package serverScreen.presentation


import core.domain.utils.Result
import core.domain.utils.ServerConnection
import core.domain.utils.ServerConnectionStatus
import core.domain.usecase.DeleteServerConnectionSettingsUseCase
import core.domain.usecase.SaveServerConnectionSettingsUseCase
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent.inject
import serverScreen.domain.CompletedTasksTable
import serverScreen.domain.TasksTable
import serverScreen.presentation.components.serverControlPanel.ServerControlPanel

class ServerScreenViewModel {
    val serverControlPanel by inject<ServerControlPanel>(ServerControlPanel::class.java)
    val serverConnection by inject<ServerConnection>(ServerConnection::class.java)
    val tasksTable by inject<TasksTable>(TasksTable::class.java)
    val completedTasksTable by inject<CompletedTasksTable>(CompletedTasksTable::class.java)
    private val saveServerConnectionSettingsUseCase by inject<SaveServerConnectionSettingsUseCase>(
        SaveServerConnectionSettingsUseCase::class.java
    )
    private val deleteServerConnectionSettingsUseCase by inject<DeleteServerConnectionSettingsUseCase>(
        DeleteServerConnectionSettingsUseCase::class.java
    )
    private var connectionJob: Job? = null


    fun connectToServer(ip: String, port: String, token: String) {
        connectionJob = CoroutineScope(Dispatchers.Default).launch {

            val result = serverConnection.loginOnServer(ip, port, token)

            if(result is Result.Success) {
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
            serverConnection.logoutFromServer()
            if(deleteServerConnectionSettingsUseCase.execute() is Result.Success)
                println("ServerConnectionSettings removed from the file storage")
        }
    }
}

