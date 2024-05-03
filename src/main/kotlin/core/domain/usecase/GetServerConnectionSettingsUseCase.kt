package core.domain.usecase

import core.data.dto.ServerConnectionSettingsDto
import core.domain.ApiError
import core.domain.Result
import core.domain.repository.ServerConnectionSettingsRepository

class GetServerConnectionSettingsUseCase(private val repository: ServerConnectionSettingsRepository) {

    suspend fun execute(): Result<ServerConnectionSettingsDto, ApiError.ServerConnectionSettingsError> {
        return repository.readSettings()
    }

}