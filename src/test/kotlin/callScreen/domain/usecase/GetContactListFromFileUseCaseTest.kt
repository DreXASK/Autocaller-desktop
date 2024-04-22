package callScreen.domain.usecase

import callScreen.domain.models.ContactTableItemData
import callScreen.domain.repository.ContactRepository
import core.presentation.utils.Sex
import callScreen.mock.data.ContactRepositoryTest
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject
import org.koin.test.junit5.AutoCloseKoinTest
import kotlin.test.assertContentEquals

class GetContactListFromFileUseCaseTest: AutoCloseKoinTest() {

    private val useCase by inject<GetContactListFromFileUseCase>(GetContactListFromFileUseCase::class.java)

    @Test
    fun `get the same contact list as was sent from test repository`() {

        startKoin {
            modules(
                module {
                    single { GetContactListFromFileUseCase(contactRepository = get()) }
                    single<ContactRepository> { ContactRepositoryTest() }
                }
            )
        }

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

        val actualList: MutableList<ContactTableItemData> = mutableListOf()
        actualList.addAll(useCase.execute(""))

        assertContentEquals(expected = expectedList, actual = actualList)
    }
}