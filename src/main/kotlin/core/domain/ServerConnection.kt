package core.domain

import androidx.compose.runtime.mutableStateOf
import core.data.serverConnectionSettings.ServerConnectionSettingsDto
import core.data.login.LoginReceiveRemote
import core.data.login.LoginResponseRemote
import core.data.tokens.RegisterReceiveRemote
import core.data.tokens.RegisterResponseRemote
import org.koin.java.KoinJavaComponent.inject
import core.domain.usecase.LoginOnServerUseCase
import core.domain.usecase.RegisterOnServerUseCase

class ServerConnection {
    private val registerOnServerUseCase by inject<RegisterOnServerUseCase>(RegisterOnServerUseCase::class.java)
    private val loginOnServerUseCase by inject<LoginOnServerUseCase>(
        LoginOnServerUseCase::class.java
    )

    var serverConnectionSettings: ServerConnectionSettingsDto? = null
        private set
    val connectionStatus = mutableStateOf(ServerConnectionStatus.DISCONNECTED)


    suspend fun registerOnServer(ip: String, port: String): Result<RegisterResponseRemote, ApiError> {
        connectionStatus.value = ServerConnectionStatus.CONNECTING
        println("ConnectionStatus set to CONNECTING")

        val tokenResult = registerOnServerUseCase.execute(RegisterReceiveRemote("secret)))"))
        println("Token requested")

        when (tokenResult) {
            is Result.Success -> {
                println("Token = ${tokenResult.data.token}")
                connectionStatus.value = ServerConnectionStatus.CONNECTED
                println("ConnectionStatus set to CONNECTED")

                serverConnectionSettings = ServerConnectionSettingsDto(
                    ip = ip,
                    port = port,
                    token = tokenResult.data.token
                )
                println("ServerConnectionSettings have been set")
            }
            is Result.Error -> {
                connectionStatus.value = ServerConnectionStatus.DISCONNECTED
                println("Token error - ${tokenResult.error}")
                println("ConnectionStatus set to DISCONNECTED")
            }
        }

        return tokenResult
    }

    suspend fun loginOnServer(ip: String, port: String, token: String): Result<LoginResponseRemote, ApiError> {
        connectionStatus.value = ServerConnectionStatus.CONNECTING
        println("ConnectionStatus set to ${ServerConnectionStatus.CONNECTING}")

        serverConnectionSettings = ServerConnectionSettingsDto(
            ip = ip,
            port = port,
            token = token
        )
        println("Variable ServerConnectionSettings has been set")

        val tokenStatusResult = loginOnServerUseCase.execute(LoginReceiveRemote(token))
        println("TokenStatus requested")

        when (tokenStatusResult) {
            is Result.Success -> {
                println("TokenStatus = ${tokenStatusResult.data.tokenStatus}")
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



