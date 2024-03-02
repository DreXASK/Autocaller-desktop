package connectionAdjusterScreen.presentation

import androidx.compose.runtime.mutableStateOf
import core.domain.ServerConnection
import org.koin.java.KoinJavaComponent.inject

class ConnectionAdjusterScreenViewModel {
    val serverConnection by inject<ServerConnection>(ServerConnection::class.java)
    val ipTextValue = mutableStateOf("")
}