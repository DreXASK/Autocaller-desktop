package core.domain.usecase

import core.domain.utils.ApiError
import core.domain.utils.Result
import core.domain.repository.ServerConnectionSettingsRepository

class DeleteServerConnectionSettingsUseCase(private val repository: ServerConnectionSettingsRepository) {

    suspend fun execute(): Result<Unit, ApiError> {
        return repository.deleteSettings()
    }

}