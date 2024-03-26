package connectionAdjusterScreen.presentation

import androidx.compose.runtime.mutableStateOf
import connectionAdjusterScreen.data.remote.dto.ClientTokenRequest
import connectionAdjusterScreen.data.remote.dto.ClientTokenResponse
import connectionAdjusterScreen.data.remote.dto.ClientTokenStatusRequest
import connectionAdjusterScreen.domain.usecase.CheckConnectionStatusUseCase
import connectionAdjusterScreen.domain.usecase.GetClientTokenUseCase
import core.domain.ServerConnection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class ConnectionAdjusterScreenViewModel {
    val serverConnection by inject<ServerConnection>(ServerConnection::class.java)
    private val getClientTokenUseCase by inject<GetClientTokenUseCase>(GetClientTokenUseCase::class.java)
    private val checkConnectionStatusUseCase by inject<CheckConnectionStatusUseCase>(CheckConnectionStatusUseCase::class.java)

    val ipTextValue = mutableStateOf("")

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