package core.domain.utils

sealed interface DataError: Error {
    enum class PlaceholdersError: DataError {
        SURNAME_IS_NULL,
        NAME_IS_NULL,
        PATRONYMIC_IS_NULL,
        SEX_IS_NULL,
        AGE_IS_NULL
    }
}