package serverScreen.domain.models

data class MessageTemplateData(
    val name: String,
    val text: String,
    val placeholders: MessageTemplatePlaceholders
)