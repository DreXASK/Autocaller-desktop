package connectionAdjusterScreen.presentation

import androidx.compose.runtime.mutableStateOf
import connectionAdjusterScreen.domain.CheckConnectionStatusUseCase
import connectionAdjusterScreen.domain.GetTokenUseCase
import core.domain.ServerConnection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class ConnectionAdjusterScreenViewModel {
    val serverConnection by inject<ServerConnection>(ServerConnection::class.java)
    private val getTokenUseCase by inject<GetTokenUseCase>(GetTokenUseCase::class.java)
    private val checkConnectionStatusUseCase by inject<CheckConnectionStatusUseCase>(CheckConnectionStatusUseCase::class.java)

    val ipTextValue = mutableStateOf("")

    fun getToken() {
        CoroutineScope(Dispatchers.Default).launch {
            println(getTokenUseCase.execute())
        }
    }
    fun checkTokenStatus() {
        CoroutineScope(Dispatchers.Default).launch {
            println(checkConnectionStatusUseCase.execute("123"))
        }
    }
}