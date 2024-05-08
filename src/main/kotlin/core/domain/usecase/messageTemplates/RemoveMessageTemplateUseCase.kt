package core.domain.usecase.messageTemplates

import core.domain.repository.messageTemplates.MessageTemplateRepository
import core.domain.repository.messageTemplates.MessageTemplateTypes
import core.domain.utils.ApiError
import core.domain.utils.Result

class RemoveMessageTemplateUseCase(private val messageTemplateRepository: MessageTemplateRepository) {

    suspend fun execute(messageTemplateDto: MessageTemplateTypes.Parameter.Remove): Result<Unit, ApiError.MessageTemplatesError> {
        val result = messageTemplateRepository.removeMessageTemplate(messageTemplateDto)
        return result
    }

}