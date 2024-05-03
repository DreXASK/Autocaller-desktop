package core.domain.repository

import core.data.dto.ServerConnectionSettingsDto
import core.domain.ApiError
import core.domain.Result

interface ServerConnectionSettingsRepository {
    suspend fun readSettings(): Result<ServerConnectionSettingsDto, ApiError.ServerConnectionSettingsError>
    suspend fun writeSettings(serverConnectionSettings: ServerConnectionSettingsDto): Result<Unit, ApiError>
    suspend fun deleteSettings(): Result<Unit, ApiError>
}