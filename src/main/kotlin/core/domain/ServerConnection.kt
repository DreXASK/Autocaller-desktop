package core.domain

import androidx.compose.runtime.mutableStateOf
import org.koin.java.KoinJavaComponent.inject
import serverScreen.data.remote.dto.ConnectionStatusRequest
import serverScreen.domain.usecase.GetConnectionStatusUseCase
import serverScreen.domain.usecase.GetTokenUseCase

class ServerConnection {
    private val getTokenUseCase by inject<GetTokenUseCase>(GetTokenUseCase::class.java)
    private val getConnectionStatusUseCase by inject<GetConnectionStatusUseCase>(
        GetConnectionStatusUseCase::class.java
    )

    val connectionStatus = mutableStateOf(ServerConnectionStatus.Disconnected)

    suspend fun getToken() {
        println(getTokenUseCase.execute())
    }

    suspend fun getConnectionStatus() {
        println(getConnectionStatusUseCase.execute(ConnectionStatusRequest("123")))
    }
}



