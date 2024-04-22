package core.domain

import androidx.compose.runtime.mutableStateOf
import org.koin.java.KoinJavaComponent.inject
import core.data.dto.TokenStatusRequest
import core.domain.usecase.GetTokenStatusUseCase
import core.domain.usecase.GetTokenUseCase

class ServerConnection {
    private val getTokenUseCase by inject<GetTokenUseCase>(GetTokenUseCase::class.java)
    private val getTokenStatusUseCase by inject<GetTokenStatusUseCase>(
        GetTokenStatusUseCase::class.java
    )

    val connectionStatus = mutableStateOf(ServerConnectionStatus.DISCONNECTED)

    suspend fun getToken() {
        println(getTokenUseCase.execute())
    }

    suspend fun getConnectionStatus() {
        println(getTokenStatusUseCase.execute(TokenStatusRequest("123")))
    }
}



