package callScreen.domain.usecase

import callScreen.domain.models.ContactData
import core.domain.repository.callTasks.CallTaskDto
import core.domain.models.MessageTemplateData
import core.domain.models.MessageTemplatePlaceholders
import core.domain.utils.DataError
import core.domain.utils.Result
import core.domain.utils.Sex
import serverScreen.presentation.components.serverControlPanel.tabs.messageTemplatesWindow.*
import java.time.OffsetDateTime
import java.time.ZoneOffset

class CreateCallTaskDtoListUseCase {

    fun execute(
        contactList: List<ContactData>,
        messageTemplateData: MessageTemplateData
    ): Result<List<CallTaskDto>, DataError.PlaceholdersError> {
        val resultList = mutableListOf<CallTaskDto>()

        val checkResult = checkIfPlaceholdersCanBeFilled(contactList, messageTemplateData.placeholders)
        if(checkResult is Result.Error)
            return Result.Error(checkResult.error)

        contactList.map {
            resultList.add(
                CallTaskDto(
                    id = null,
                    surname = it.surname,
                    name = it.name,
                    patronymic = it.patronymic,
                    phoneNumber = it.phoneNumber,
                    messageText = getTextWithFilledPlaceholders(it, messageTemplateData),
                    callAttempts = 0,
                    nextCallDateTimeUTC = OffsetDateTime.now(ZoneOffset.UTC)
                )
            )
        }

        return Result.Success(resultList)
    }

    private fun checkIfPlaceholdersCanBeFilled(
        contactList: List<ContactData>,
        placeholders: MessageTemplatePlaceholders
    ): Result<Unit, DataError.PlaceholdersError> {
        contactList.map {
            when {
                placeholders.isSurnamePlaceholderUsed && it.surname == null -> return Result.Error(DataError.PlaceholdersError.SURNAME_IS_NULL)
                placeholders.isNamePlaceholderUsed && it.name == null -> return Result.Error(DataError.PlaceholdersError.NAME_IS_NULL)
                placeholders.isPatronymicPlaceholderUsed && it.patronymic == null -> return Result.Error(DataError.PlaceholdersError.PATRONYMIC_IS_NULL)
                placeholders.isSexPlaceholderUsed && it.sex == null -> return Result.Error(DataError.PlaceholdersError.SEX_IS_NULL)
                placeholders.isAgePlaceholderUsed && it.age == null -> return Result.Error(DataError.PlaceholdersError.AGE_IS_NULL)
                else -> {}
            }
        }
        return Result.Success(Unit)
    }

    private fun getTextWithFilledPlaceholders(contactData: ContactData, messageTemplateData: MessageTemplateData): String {
        var resultText = messageTemplateData.text

        messageTemplateData.apply {
            if(placeholders.isSurnamePlaceholderUsed)
                resultText = resultText.replace(SURNAME_PLACE_HOLDER, contactData.surname!!)
            if(placeholders.isNamePlaceholderUsed)
                resultText = resultText.replace(NAME_PLACE_HOLDER, contactData.name!!)
            if(placeholders.isPatronymicPlaceholderUsed)
                resultText = resultText.replace(PATRONYMIC_PLACE_HOLDER, contactData.patronymic!!)
            if(placeholders.isSurnamePlaceholderUsed)
                resultText = resultText.replace(PHONE_NUMBER_PLACE_HOLDER, contactData.phoneNumber)
            if(placeholders.isSurnamePlaceholderUsed)
                resultText = resultText.replace(SEX_PLACE_HOLDER, if(contactData.sex == Sex.MALE) "мужской" else "женский")
            if(placeholders.isSurnamePlaceholderUsed)
                resultText = resultText.replace(AGE_PLACE_HOLDER, contactData.age!!.toString())
        }

        return resultText
    }
}
