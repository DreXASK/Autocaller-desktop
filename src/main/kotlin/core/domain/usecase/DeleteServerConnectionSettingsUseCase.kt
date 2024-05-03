package core.domain.usecase

import core.domain.ApiError
import core.domain.Result
import core.domain.repository.ServerConnectionSettingsRepository

class DeleteServerConnectionSettingsUseCase(private val repository: ServerConnectionSettingsRepository) {

    suspend fun execute(): Result<Unit, ApiError> {
        return repository.deleteSettings()
    }

}