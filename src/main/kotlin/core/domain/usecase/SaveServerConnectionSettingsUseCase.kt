package core.domain.usecase

import core.data.serverConnectionSettings.ServerConnectionSettingsDto
import core.domain.ApiError
import core.domain.Result
import core.domain.repository.ServerConnectionSettingsRepository

class SaveServerConnectionSettingsUseCase(private val repository: ServerConnectionSettingsRepository) {

    suspend fun execute(settings: ServerConnectionSettingsDto): Result<Unit, ApiError> {
        return repository.writeSettings(settings)
    }

}