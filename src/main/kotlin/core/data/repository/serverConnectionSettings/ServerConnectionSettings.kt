package core.data.repository.serverConnectionSettings

import kotlinx.serialization.Serializable

@Serializable
data class ServerConnectionSettingsDto(
    val ip: String,
    val port: String,
    val token: String
)