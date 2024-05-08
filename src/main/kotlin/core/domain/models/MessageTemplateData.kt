package core.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class MessageTemplateData(
    val id: Long?,
    val name: String,
    val text: String,
    val placeholders: MessageTemplatePlaceholders
)