package callScreen.domain.usecase

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import callScreen.di.callScreenModule
import callScreen.domain.models.ContactTableItemData
import callScreen.presentation.components.contactTable.ContactTableFilterStore
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject
import kotlin.test.Test
import kotlin.test.assertContentEquals

class GetFilteredContactListUseCaseTest {

    private val useCase by inject<GetFilteredContactListUseCase>(GetFilteredContactListUseCase::class.java)

    @Test
    fun `should return filtered list`() {

        startKoin {
            modules(callScreenModule)
        }

        val initialContactList = mutableStateListOf(
            ContactTableItemData(
                "Иванов",
                "Иван",
                "Иванович",
                "89025768472",
                "м",
                20
            ),
            ContactTableItemData(
                "Петров",
                "Георгий",
                "Константинович",
                "89022667325",
                "м",
                50
            ),
            ContactTableItemData(
                "Иванова",
                "Ольга",
                "Руслановна",
                "89025324512",
                "ж",
                80
            ),
        )
        val filterStore = ContactTableFilterStore(
            surnameFilterText = mutableStateOf("Иванов"),
            nameFilterText = mutableStateOf(""),
            patronymicFilterText = mutableStateOf(""),
            numberFilterText = mutableStateOf(""),
            genderFilterText = mutableStateOf("ж"),
            ageMinFilterText = mutableStateOf("50")
        )

        val expectedList = listOf(
            ContactTableItemData(
                "Иванова",
                "Ольга",
                "Руслановна",
                "89025324512",
                "ж",
                80
            )
        )

        val actualList =  useCase.execute(initialContactList, filterStore)

        assertContentEquals(expectedList, actualList)
    }
}