package core.domain.usecase.serverConnectionSettings

import core.domain.models.ServerConnectionSettingsDto
import core.domain.utils.ApiError
import core.domain.utils.Result
import core.domain.repository.ServerConnectionSettingsRepository

class SaveServerConnectionSettingsUseCase(private val repository: ServerConnectionSettingsRepository) {

    suspend fun execute(settings: ServerConnectionSettingsDto): Result<Unit, ApiError> {
        return repository.writeSettings(settings)
    }

}