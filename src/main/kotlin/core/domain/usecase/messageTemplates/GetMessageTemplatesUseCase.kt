package core.domain.usecase.messageTemplates

import core.domain.models.MessageTemplateData
import core.domain.repository.messageTemplates.MessageTemplateRepository
import core.domain.repository.messageTemplates.MessageTemplateTypes
import core.domain.utils.ApiError
import core.domain.utils.Result

class GetMessageTemplatesUseCase(private val messageTemplateRepository: MessageTemplateRepository) {

    suspend fun execute(messageTemplateDto: MessageTemplateTypes.Parameter.Get): Result<List<MessageTemplateData>, ApiError.MessageTemplatesError> {
        return when(val result = messageTemplateRepository.getMessageTemplateList(messageTemplateDto)) {
            is Result.Success -> Result.Success(result.data)
            is Result.Error -> Result.Error(result.error)
        }
    }

}