package core.domain.repository

import core.data.repository.serverConnectionSettings.ServerConnectionSettingsDto
import core.domain.utils.ApiError
import core.domain.utils.Result

interface ServerConnectionSettingsRepository {
    suspend fun readSettings(): Result<ServerConnectionSettingsDto, ApiError.ServerConnectionSettingsError>
    suspend fun writeSettings(serverConnectionSettings: ServerConnectionSettingsDto): Result<Unit, ApiError>
    suspend fun deleteSettings(): Result<Unit, ApiError>
}