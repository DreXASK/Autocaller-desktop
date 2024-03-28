package serverScreen.presentation.components.serverControlPanel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class ServerControlPanelSettings {
    var windowToDisplay = mutableStateOf(ServerControlPanelWindows.ServerControlPanel)
}

enum class ServerControlPanelWindows {
    ServerControlPanel,
    UserProfileWindow
}