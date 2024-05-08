package core.domain.usecase.messageTemplates

import core.domain.repository.messageTemplates.MessageTemplateRepository
import core.domain.repository.messageTemplates.MessageTemplateTypes
import core.domain.utils.ApiError
import core.domain.utils.Result

class SendMessageTemplateUseCase(private val messageTemplateRepository: MessageTemplateRepository) {

    suspend fun execute(messageTemplateDto: MessageTemplateTypes.Parameter.Send): Result<Unit, ApiError.MessageTemplatesError> {
        val result = messageTemplateRepository.sendMessageTemplate(messageTemplateDto)
        return result
    }

}