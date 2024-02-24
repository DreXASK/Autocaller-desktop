package network

import androidx.compose.runtime.mutableStateOf

object Connection {
    val connectionStatus = mutableStateOf(ConnectionStatus.Disconnected)
}

enum class ConnectionStatus(val text: String) {
    Connected("Подключено"),
    PendingConnection("Ожидание подключения"),
    Disconnected("Не подключено")
}