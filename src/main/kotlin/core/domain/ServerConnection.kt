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

        val tokenResult = getTokenUseCase.execute(TokenRequest("secret)))"))
        when (tokenResult) {
            is Result.Success -> {
                connectionStatus.value = ServerConnectionStatus.CONNECTED

                serverConnectionSettings = ServerConnectionSettingsDto(
                    ip = ip,
                    port = port,
                    token = tokenResult.data.token
                )
            }

            is Result.Error -> {
                connectionStatus.value = ServerConnectionStatus.DISCONNECTED
            }
        }

        return tokenResult
    }

    suspend fun loginOnServer(ip: String, port: String, token: String): Result<TokenStatusResponse, ApiError> {
        connectionStatus.value = ServerConnectionStatus.CONNECTING

        serverConnectionSettings = ServerConnectionSettingsDto(
            ip = ip,
            port = port,
            token = token
        )

        val tokenStatusResult = getTokenStatusUseCase.execute(TokenStatusRequest(token))
        when (tokenStatusResult) {
            is Result.Success -> {
                connectionStatus.value = ServerConnectionStatus.CONNECTED
                println(tokenStatusResult.data.tokenStatus)
            }

            is Result.Error -> {
                connectionStatus.value = ServerConnectionStatus.DISCONNECTED
                println("Could not connect to the server")
            }
        }

        return tokenStatusResult
    }

    fun logoutFromServer() {
        connectionStatus.value = ServerConnectionStatus.DISCONNECTED
        serverConnectionSettings = null
    }
}



