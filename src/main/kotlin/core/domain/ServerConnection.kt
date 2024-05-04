package core.domain

import androidx.compose.runtime.mutableStateOf
import core.data.dto.*
import org.koin.java.KoinJavaComponent.inject
import core.domain.usecase.GetTokenStatusUseCase
import core.domain.usecase.GetTokenUseCase

class ServerConnection {
    private val getTokenUseCase by inject<GetTokenUseCase>(GetTokenUseCase::class.java)
    private val getTokenStatusUseCase by inject<GetTokenStatusUseCase>(
        GetTokenStatusUseCase::class.java
    )

    var serverConnectionSettings: ServerConnectionSettingsDto? = null
        private set
    val connectionStatus = mutableStateOf(ServerConnectionStatus.DISCONNECTED)


    suspend fun registerOnServer(ip: String, port: String): Result<TokenResponse, ApiError> {
        connectionStatus.value = ServerConnectionStatus.CONNECTING
        println("ConnectionStatus set to CONNECTING")

        val tokenResult = getTokenUseCase.execute(TokenRequest("secret)))"))
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

    suspend fun loginOnServer(ip: String, port: String, token: String): Result<TokenStatusResponse, ApiError> {
        connectionStatus.value = ServerConnectionStatus.CONNECTING
        println("ConnectionStatus set to CONNECTING")

        serverConnectionSettings = ServerConnectionSettingsDto(
            ip = ip,
            port = port,
            token = token
        )
        println("ServerConnectionSettings have been set")

        val tokenStatusResult = getTokenStatusUseCase.execute(TokenStatusRequest(token))
        println("TokenStatus requested")

        when (tokenStatusResult) {
            is Result.Success -> {
                println("TokenStatus = ${tokenStatusResult.data.tokenStatus}")
                connectionStatus.value = ServerConnectionStatus.CONNECTED
                println("ConnectionStatus set to CONNECTED")
            }
            is Result.Error -> {
                connectionStatus.value = ServerConnectionStatus.DISCONNECTED
                println("TokenStatus error - ${tokenStatusResult.error}")
                println("ConnectionStatus set to DISCONNECTED")
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



