package core.domain

import androidx.compose.runtime.mutableStateOf
import core.domain.models.ServerConnectionSettingsDto
import core.data.repository.login.LoginParameterRemote
import org.koin.java.KoinJavaComponent.inject
import core.domain.usecase.LoginOnServerUseCase
import core.domain.utils.ApiError
import core.domain.utils.Result
import core.domain.utils.ServerConnectionStatus
import core.domain.utils.TokenStatus

class ServerConnection {
    private val loginOnServerUseCase by inject<LoginOnServerUseCase>(
        LoginOnServerUseCase::class.java
    )

    var serverConnectionSettings: ServerConnectionSettingsDto? = null
        private set
    val connectionStatus = mutableStateOf(ServerConnectionStatus.DISCONNECTED)

    suspend fun loginOnServer(ip: String, port: String, token: String): Result<TokenStatus, ApiError> {
        connectionStatus.value = ServerConnectionStatus.CONNECTING
        println("ConnectionStatus set to ${ServerConnectionStatus.CONNECTING}")

        serverConnectionSettings = ServerConnectionSettingsDto(
            ip = ip,
            port = port,
            token = token
        )
        println("Variable ServerConnectionSettings has been set")

        val tokenStatusResult = loginOnServerUseCase.execute(LoginParameterRemote(token))
        println("TokenStatus requested")

        when (tokenStatusResult) {
            is Result.Success -> {
                println("TokenStatus = ${tokenStatusResult.data}")
                connectionStatus.value = ServerConnectionStatus.CONNECTED
                println("ConnectionStatus set to ${ServerConnectionStatus.CONNECTED}")
            }
            is Result.Error -> {
                connectionStatus.value = ServerConnectionStatus.DISCONNECTED
                println("TokenStatus error - ${tokenStatusResult.error}")
                println("ConnectionStatus set to ${ServerConnectionStatus.DISCONNECTED}")
            }
        }

        return tokenStatusResult
    }

    fun logoutFromServer() {
        connectionStatus.value = ServerConnectionStatus.DISCONNECTED
        println("ConnectionStatus set to DISCONNECTED")
        serverConnectionSettings = null
        println("ServerConnectionSettings nullified")
    }
}



