package core.domain.utils

sealed interface ApiError: Error {
    enum class TokenStatusError: ApiError {
        INVALID_TOKEN
    }
    enum class ServerConnectionSettingsError: ApiError {
        FILE_DOES_NOT_EXIST
    }
    sealed interface ContactsError: ApiError {
        sealed interface Local: ContactsError {
            data class UnknownError(val exception: Exception): Local
        }
        sealed interface Remote: ContactsError {
            data class UnknownError(val text: String): Remote
        }
    }
    sealed interface CallTasksError: ApiError {
        sealed interface Remote: CallTasksError {
            data class UnknownError(val text: String): Remote
        }
    }
    sealed interface MessageTemplatesError: ApiError {
        sealed interface Remote: MessageTemplatesError {
            data class UnknownError(val text: String): Remote
        }
    }
    sealed interface CompletedTasksError: ApiError {
        sealed interface Remote: CompletedTasksError {
            data class UnknownError(val text: String): Remote
        }
    }
    sealed interface CallProcessSettings: ApiError {
        sealed interface Remote: CallProcessSettings {
            data class UnknownError(val text: String): Remote
        }
    }
    enum class Network: ApiError {
        CONNECTION_REFUSED,
        REQUEST_TIMEOUT
    }

    data class UnknownError(val text: String) : ApiError
}