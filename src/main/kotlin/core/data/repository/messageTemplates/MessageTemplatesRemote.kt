package core.data.repository.messageTemplates

import core.domain.models.MessageTemplateData
import core.domain.repository.messageTemplates.MessageTemplateTypes
import kotlinx.serialization.Serializable

@Serializable
data class MessageTemplateParameterGetRemote(
    val token: String
): MessageTemplateTypes.Parameter.Get

@Serializable
data class MessageTemplateParameterRemoveRemote(
    val token: String,
    val id: Long
): MessageTemplateTypes.Parameter.Remove

@Serializable
data class MessageTemplateParameterSendRemote(
    val token: String,
    val messageTemplate: MessageTemplateData
): MessageTemplateTypes.Parameter.Send