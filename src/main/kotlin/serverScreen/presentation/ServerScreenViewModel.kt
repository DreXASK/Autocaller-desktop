package serverScreen.presentation


import core.data.repository.callTasks.CallTasksParameterGetRemote
import core.data.repository.callTasks.CallTasksParameterRemoveRemote
import core.domain.models.CallTaskData
import core.domain.utils.Result
import core.domain.utils.ServerConnection
import core.domain.utils.ServerConnectionStatus
import core.domain.usecase.DeleteServerConnectionSettingsUseCase
import core.domain.usecase.GetCallTaskDataListUseCase
import core.domain.usecase.SaveServerConnectionSettingsUseCase
import core.domain.utils.ApiError
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent.get
import org.koin.java.KoinJavaComponent.inject
import serverScreen.domain.CompletedTasksTable
import serverScreen.domain.CallTasksTable
import serverScreen.domain.usecase.RemoveCallTaskUseCase
import serverScreen.presentation.components.serverControlPanel.ServerControlPanel

class ServerScreenViewModel {
    val serverControlPanel by inject<ServerControlPanel>(ServerControlPanel::class.java)
    val serverConnection by inject<ServerConnection>(ServerConnection::class.java)
    val callTasksTable by inject<CallTasksTable>(CallTasksTable::class.java)
    val completedTasksTable by inject<CompletedTasksTable>(CompletedTasksTable::class.java)

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

    suspend fun getTaskListFromServer(): List<CallTaskData>? {
        val getCallTaskDataListUseCase = get<GetCallTaskDataListUseCase>(GetCallTaskDataListUseCase::class.java)
        val callTaskDtoParameter = serverConnection.serverConnectionSettings?.let { CallTasksParameterGetRemote(it.token) } ?: return null

        when(val result = getCallTaskDataListUseCase.execute(callTaskDtoParameter)) {
            is Result.Success -> {
                return result.data
            }
            is Result.Error -> {
                when(result.error) {
                    is ApiError.CallTasksError.Remote.UnknownError -> {
                        println("GetCallTasksListUseCase error - ${result.error}")
                        return null
                        //TODO()
                    }
                }
            }
        }
    }

    fun removeCallTaskFromServer(id: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val useCase = get<RemoveCallTaskUseCase>(RemoveCallTaskUseCase::class.java)
            val callTaskDtoParameter = serverConnection.serverConnectionSettings?.let { CallTasksParameterRemoveRemote(it.token, id) } ?: return@launch

            when(val result = useCase.execute(callTaskDtoParameter)) {
                is Result.Success -> {
                    callTasksTable.removeTask(id)
                    println("CallTask with id = $id was successfully removed from the server")
                    //TODO()
                }
                is Result.Error -> {
                    when(result.error) {
                        is ApiError.CallTasksError.Remote.UnknownError -> {
                            println("RemoveCallTaskUseCase error - ${result.error}")
                            //TODO()
                        }
                    }
                }
            }
        }
    }
}

