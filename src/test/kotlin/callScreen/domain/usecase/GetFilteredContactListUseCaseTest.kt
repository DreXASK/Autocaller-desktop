package callScreen.domain.usecase

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import callScreen.domain.models.ContactTableItemData
import callScreen.presentation.components.contactTable.ContactTableFilterStore
import core.presentation.utils.Sex
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject
import org.koin.test.junit5.AutoCloseKoinTest
import kotlin.test.assertContentEquals


class GetFilteredContactListUseCaseTest: AutoCloseKoinTest() {

    private val useCase by inject<GetFilteredContactListUseCase>(GetFilteredContactListUseCase::class.java)

    @Test
    fun `return filtered contact list`() {

        startKoin {
            modules(
                module {
                    single { GetFilteredContactListUseCase() }
                }
            )
        }

        val initialContactList = mutableStateListOf(
            ContactTableItemData(
                "Иванов",
                "Иван",
                "Иванович",
                "89025768472",
                Sex.MALE,
                20
            ),
            ContactTableItemData(
                "Петров",
                "Георгий",
                "Константинович",
                "89022667325",
                Sex.MALE,
                50
            ),
            ContactTableItemData(
                "Иванова",
                "Ольга",
                "Руслановна",
                "89025324512",
                Sex.FEMALE,
                80
            ),
        )
        val filterStore = ContactTableFilterStore(
            surnameFilterText = mutableStateOf("Иванов"),
            nameFilterText = mutableStateOf(""),
            patronymicFilterText = mutableStateOf(""),
            phoneNumberFilterText = mutableStateOf(""),
            sexFilterSelector = mutableStateOf(Sex.FEMALE),
            ageMinFilterText = mutableStateOf("50")
        )

        val expectedList = listOf(
            ContactTableItemData(
                "Иванова",
                "Ольга",
                "Руслановна",
                "89025324512",
                Sex.FEMALE,
                80
            )
        )

        val actualList =  useCase.execute(initialContactList, filterStore)

        assertContentEquals(expected = expectedList, actual = actualList)
    }
}