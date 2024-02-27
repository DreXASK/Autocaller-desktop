package viewModels

import androidx.compose.runtime.mutableStateOf
import ui.screenModes.ConnectionScreenModes

class ConnectionScreenViewModel {
    val screenMode = mutableStateOf(ConnectionScreenModes.Disconnected)
    val ipTextValue = mutableStateOf("")
}