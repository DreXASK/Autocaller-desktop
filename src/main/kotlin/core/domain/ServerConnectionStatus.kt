package core.domain

enum class ServerConnectionStatus {
    Connected,
    Connecting,
    PendingToken,
    Disconnected
}