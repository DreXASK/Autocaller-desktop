package callScreen.di

sealed interface Qualifiers {
    enum class FileTypes: Qualifiers {
        JSON,
        CSV
    }
    enum class Remoteness: Qualifiers {
        LOCAL,
        REMOTE
    }
}