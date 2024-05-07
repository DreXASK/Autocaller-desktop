package unit.callScreen.domain.usecase

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import callScreen.domain.models.ContactData
import callScreen.domain.usecase.GetFilteredContactListUseCase
import callScreen.presentation.components.contactTable.ContactTableFilterStore
import core.domain.utils.Sex
import io.mockk.clearMocks
import io.mockk.spyk
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals


class GetFilteredContactListUseCaseQualifiersTest {

    private val useCase = spyk<GetFilteredContactListUseCase>()

    @get:Rule
    val filterStore = ContactTableFilterStore(
        surnameFilterText = mutableStateOf("Иванов"),
        nameFilterText = mutableStateOf(""),
        patronymicFilterText = mutableStateOf(""),
        phoneNumberFilterText = mutableStateOf(""),
        sexFilterSelector = mutableStateOf(Sex.FEMALE),
        ageMinFilterText = mutableStateOf("50")
    )

    @get:Rule
    val initialContactList = mutableStateListOf(
        ContactData(
            "Иванов",
            "Иван",
            "Иванович",
            "89025768472",
            Sex.MALE,
            20
        ),
        ContactData(
            "Петров",
            "Георгий",
            "Константинович",
            "89022667325",
            Sex.MALE,
            50
        ),
        ContactData(
            "Иванова",
            "Ольга",
            "Руслановна",
            "89025324512",
            Sex.FEMALE,
            80
        ),
    )


    @BeforeEach
    fun clear() {
        clearMocks(useCase)
    }


    @Test
    fun `return filtered contact list`() {

        val expectedList = listOf(
            ContactData(
                "Иванова",
                "Ольга",
                "Руслановна",
                "89025324512",
                Sex.FEMALE,
                80
            )
        )

        val actualList = useCase.execute(initialContactList, filterStore)

        assertContentEquals(expected = expectedList, actual = actualList)
    }
}