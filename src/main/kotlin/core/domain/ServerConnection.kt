package core.domain

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import serverScreen.data.remote.dto.ClientTokenStatusRequest
import serverScreen.domain.usecase.CheckConnectionStatusUseCase
import serverScreen.domain.usecase.GetClientTokenUseCase

class ServerConnection {
    private val getClientTokenUseCase by inject<GetClientTokenUseCase>(GetClientTokenUseCase::class.java)
    private val checkConnectionStatusUseCase by inject<CheckConnectionStatusUseCase>(
        CheckConnectionStatusUseCase::class.java
    )

    val connectionStatus = mutableStateOf(ServerConnectionStatus.Disconnected)

    fun getToken() {
        CoroutineScope(Dispatchers.Default).launch {
            println(getClientTokenUseCase.execute())
        }
    }
    fun checkTokenStatus() {
        CoroutineScope(Dispatchers.Default).launch {
            println(checkConnectionStatusUseCase.execute(ClientTokenStatusRequest("123")))
        }
    }
}



