package callScreen.domain.usecase

import androidx.compose.runtime.snapshots.SnapshotStateList
import callScreen.domain.models.ContactData
import callScreen.presentation.components.contactTable.ContactTableFilterStore
import core.domain.utils.Sex

class GetFilteredContactListUseCase {

    fun execute(
        contactList: SnapshotStateList<ContactData>,
        filterStore: ContactTableFilterStore
    ): List<ContactData> {

        val callTableContactListFiltered = mutableListOf<ContactData>()

        callTableContactListFiltered.addAll(
            contactList.filter { contact ->
                isFieldContainsFilterText(contact.surname, filterStore.surnameFilterText.value)
                        && isFieldContainsFilterText(contact.name, filterStore.nameFilterText.value)
                        && isFieldContainsFilterText(contact.patronymic, filterStore.patronymicFilterText.value)
                        && isFieldContainsFilterText(contact.phoneNumber, filterStore.phoneNumberFilterText.value)
                        && isSexIsTheSame(contact.sex, filterStore.sexFilterSelector.value)
                        && (isAgeFieldsAreEmpty(filterStore)
                        || isAgeBiggerThanMinAgeAndSmallerThanMaxAge(contact.age, filterStore)
                        || (isOnlyMinAgeIsExists(filterStore) && isAgeBiggerThanMinAge(contact.age, filterStore))
                        || (isOnlyMaxAgeIsExists(filterStore) && isAgeSmallerThanMaxAge(contact.age, filterStore)))
            }
        )

        return callTableContactListFiltered
    }
}

private fun isFieldContainsFilterText(
    fieldText: String?,
    filterText: String
): Boolean {
    if (filterText.isBlank())
        return true

    return fieldText?.contains(filterText, ignoreCase = true) ?: false
}

private fun isSexIsTheSame(
    sex: Sex?,
    filterValue: Sex?
): Boolean {
    if (filterValue == null)
        return true

    return sex == filterValue
}

private fun isAgeFieldsAreEmpty(filterStore: ContactTableFilterStore): Boolean =
    filterStore.ageMinFilterText.value.isEmpty() && filterStore.ageMaxFilterText.value.isEmpty()

private fun isAgeBiggerThanMinAgeAndSmallerThanMaxAge(
    age: Int?,
    filterStore: ContactTableFilterStore
): Boolean = isAgeBiggerThanMinAge(age, filterStore) && isAgeSmallerThanMaxAge(age, filterStore)

private fun isAgeBiggerThanMinAge(
    age: Int?,
    filterStore: ContactTableFilterStore
): Boolean {
    if (filterStore.ageMinFilterText.value.toIntOrNull() == null)
        return true
    if (age == null)
        return false

    return age > (filterStore.ageMinFilterText.value.toIntOrNull() ?: 0)
}

private fun isAgeSmallerThanMaxAge(
    age: Int?,
    filterStore: ContactTableFilterStore
): Boolean {
    if (filterStore.ageMaxFilterText.value.toIntOrNull() == null)
        return true
    if (age == null)
        return false

    return age < (filterStore.ageMaxFilterText.value.toIntOrNull() ?: Int.MAX_VALUE)
}

private fun isOnlyMaxAgeIsExists(filterStore: ContactTableFilterStore): Boolean =
    filterStore.ageMinFilterText.value.isEmpty() && filterStore.ageMaxFilterText.value.isNotEmpty()


private fun isOnlyMinAgeIsExists(filterStore: ContactTableFilterStore): Boolean =
    filterStore.ageMinFilterText.value.isNotEmpty() && filterStore.ageMaxFilterText.value.isEmpty()
