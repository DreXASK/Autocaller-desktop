package unit.callScreen.domain.usecase

import androidx.compose.runtime.mutableStateListOf
import callScreen.domain.models.ContactData
import callScreen.domain.repository.contacts.ContactRepository
import callScreen.domain.usecase.GetContactListUseCase
import core.domain.utils.Sex
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class FileTypesTest {

    private val contactRepository = mockk<ContactRepository>()
    private val useCase = GetContactListUseCase(contactRepository)

    @get:Rule
    val expectedList = mutableStateListOf(
        ContactData(
            null,
            "John",
            null,
            "88005553535",
            null,
            50
        ),
        ContactData(
            "SurnameTest",
            "Ivan",
            null,
            "88005553536",
            null,
            null
        ),
        ContactData(
            "Petrov",
            "Petr",
            "Petrovich",
            "88005553537",
            sex = Sex.MALE,
            20
        )
    )

    @Test
    fun `get the same contact list as was sent from test repository`() {
        every { contactRepository.getContactList(any()) } returns expectedList

        val actualList: MutableList<ContactData> = mutableListOf()
        actualList.addAll(useCase.execute(""))

        assertContentEquals(expected = expectedList, actual = actualList)
    }
}