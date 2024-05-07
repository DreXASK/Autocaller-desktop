package callScreen.domain.usecase

import callScreen.domain.models.ContactData
import core.domain.models.CallTaskData
import core.domain.models.MessageTemplateData
import core.domain.models.MessageTemplatePlaceholders

class CreateCallTaskList {

    fun execute(contactList: List<ContactData>, messageTemplateData: MessageTemplateData): List<CallTaskData> {
        val resultList = mutableListOf<CallTaskData>()

        contactList.map {
            resultList.add(
                CallTaskData(
                    surname = it.surname,
                    name = it.name,
                    patronymic = it.patronymic,
                    phoneNumber = it.phoneNumber,
                    me
                )
            )
        }
    }

    private fun checkIfPlaceholdersHaveRequiredParameters(
        contactList: List<ContactData>,
        placeholders: MessageTemplatePlaceholders
    ): Boolean {
        contactList.map {
            if ((placeholders.isSurnamePlaceholderUsed && it.surname == null)
                || (placeholders.isNamePlaceholderUsed && it.name == null)
                || (placeholders.isPatronymicPlaceholderUsed && it.patronymic == null)
                || (placeholders.isSexPlaceholderUsed && it.sex == null)
                || (placeholders.isAgePlaceholderUsed && it.age == null)
            ) return false
        }
        return true
    }

}