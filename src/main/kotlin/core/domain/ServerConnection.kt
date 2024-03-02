package core.domain

import androidx.compose.runtime.mutableStateOf

class ServerConnection {
    val connectionStatus = mutableStateOf(ServerConnectionStatus.Disconnected)
}

enum class ServerConnectionStatus(val text: String) {
    Connected("Подключено"),
    Connecting("Подключение к серверу..."),
    PendingToken("Ожидание разрешения от сервера"),
    Disconnected("Не подключено")
}