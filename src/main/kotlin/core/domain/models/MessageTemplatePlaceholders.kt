package core.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class MessageTemplatePlaceholders(
    val isSurnamePlaceholderUsed: Boolean = false,
    val isNamePlaceholderUsed: Boolean = false,
    val isPatronymicPlaceholderUsed: Boolean = false,
    val isPhoneNumberPlaceholderUsed: Boolean = false,
    val isSexPlaceholderUsed: Boolean = false,
    val isAgePlaceholderUsed: Boolean = false
)
