package callScreen.domain.usecase

import callScreen.domain.models.ContactTableItemData
import callScreen.domain.repository.ContactRepository
import core.presentation.utils.Sex
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class GetContactListFromFileUseCaseTest {

    private val contactRepository = mockk<ContactRepository>()
    private val useCase = GetContactListFromFileUseCase(contactRepository)

    @Test
    fun `get the same contact list as was sent from test repository`() {

        val expectedList = mutableListOf(
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

        every { contactRepository.getContactList(any()) } returns expectedList

        val actualList: MutableList<ContactTableItemData> = mutableListOf()
        actualList.addAll(useCase.execute(""))

        assertContentEquals(expected = expectedList, actual = actualList)
    }
}