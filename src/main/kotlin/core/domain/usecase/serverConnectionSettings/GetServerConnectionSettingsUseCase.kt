package core.domain.usecase.serverConnectionSettings

import core.data.repository.serverConnectionSettings.ServerConnectionSettingsDto
import core.domain.utils.ApiError
import core.domain.utils.Result
import core.domain.repository.ServerConnectionSettingsRepository

class GetServerConnectionSettingsUseCase(private val repository: ServerConnectionSettingsRepository) {

    suspend fun execute(): Result<ServerConnectionSettingsDto, ApiError.ServerConnectionSettingsError> {
        return repository.readSettings()
    }

}