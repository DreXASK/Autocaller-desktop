package core.domain.repository.messageTemplates

import core.domain.models.MessageTemplateData
import core.domain.repository.callTasks.CallTaskDto
import core.domain.utils.ApiError
import core.domain.utils.Result

interface MessageTemplateRepository {
    suspend fun getMessageTemplateList(parameter: MessageTemplateTypes.Parameter.Get): Result<List<MessageTemplateData>, ApiError.MessageTemplatesError>

    suspend fun removeMessageTemplate(parameter: MessageTemplateTypes.Parameter.Remove): Result<Unit, ApiError.MessageTemplatesError>

    suspend fun sendMessageTemplate(parameter: MessageTemplateTypes.Parameter.Send): Result<Unit, ApiError.MessageTemplatesError>
}