package core.domain

import androidx.compose.runtime.mutableStateListOf
import core.data.repository.messageTemplates.MessageTemplateParameterGetRemote
import core.data.repository.messageTemplates.MessageTemplateParameterRemoveRemote
import core.data.repository.messageTemplates.MessageTemplateParameterSendRemote
import core.domain.models.MessageTemplateData
import core.domain.usecase.messageTemplates.GetMessageTemplatesUseCase
import core.domain.usecase.messageTemplates.RemoveMessageTemplateUseCase
import core.domain.usecase.messageTemplates.SendMessageTemplateUseCase
import core.domain.utils.ApiError
import core.domain.utils.Result
import org.koin.java.KoinJavaComponent.get

class MessageTemplateService {

    private val _messageTemplateList = mutableStateListOf<MessageTemplateData>()
    val messageTemplateList: List<MessageTemplateData>
        get() = _messageTemplateList


    suspend fun getMessageTemplatesFromServer(messageTemplateDtoParameter: MessageTemplateParameterGetRemote): Result<List<MessageTemplateData>, ApiError.MessageTemplatesError> {
        val useCase = get<GetMessageTemplatesUseCase>(GetMessageTemplatesUseCase::class.java)
        val result = useCase.execute(messageTemplateDtoParameter)
        return result
    }

    suspend fun sendMessageTemplateToServer(messageTemplateDtoParameter: MessageTemplateParameterSendRemote): Result<Unit, ApiError.MessageTemplatesError> {
        val useCase = get<SendMessageTemplateUseCase>(SendMessageTemplateUseCase::class.java)
        val result = useCase.execute(messageTemplateDtoParameter)
        return result
    }

    suspend fun removeMessageTemplateFromServer(messageTemplateDtoParameter: MessageTemplateParameterRemoveRemote): Result<Unit, ApiError. MessageTemplatesError> {
        val useCase = get<RemoveMessageTemplateUseCase>(RemoveMessageTemplateUseCase::class.java)
        val result = useCase.execute(messageTemplateDtoParameter)
        return result
    }

    fun addMessageTemplates(messageTemplateData: MessageTemplateData) {
        _messageTemplateList.add(messageTemplateData)
    }

    fun addMessageTemplates(messageTemplateDataList: List<MessageTemplateData>) {
        _messageTemplateList.addAll(messageTemplateDataList)
    }

    fun removeMessageTemplateById(id: Long) {
        _messageTemplateList.removeIf { it.id == id }
    }

    fun clearMessageTemplates() {
        _messageTemplateList.clear()
    }
}