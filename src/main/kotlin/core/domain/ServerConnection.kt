package core.domain

import androidx.compose.runtime.mutableStateOf

class ServerConnection {
    val connectionStatus = mutableStateOf(ServerConnectionStatus.Disconnected)
}

enum class ServerConnectionStatus(val text: String) {
    Connected("Подключено"),
    PendingConnection("Ожидание подключения"),
    Disconnected("Не подключено")
}