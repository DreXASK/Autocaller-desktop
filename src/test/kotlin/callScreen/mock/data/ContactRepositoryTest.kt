package callScreen.mock.data

import callScreen.domain.models.ContactTableItemData
import callScreen.domain.repository.ContactRepository
import core.presentation.utils.Sex

class ContactRepositoryTest: ContactRepository {

    override fun getContactList(url: String): List<ContactTableItemData> {
        return listOf(
            ContactTableItemData(
                null,
                "John",
                null,
                "88005553535",
                null,
                50
            ),
            ContactTableItemData(
                "SurnameTest",
                "Ivan",
                null,
                "88005553536",
                null,
                null
            ),
            ContactTableItemData(
                "Petrov",
                "Petr",
                "Petrovich",
                "88005553537",
                sex = Sex.MALE,
                20
            )
        )
    }

    override fun sendContactListAsJson() {
        TODO("Not yet implemented")
    }

}