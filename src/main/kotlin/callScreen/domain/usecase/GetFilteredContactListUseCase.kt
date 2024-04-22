package callScreen.domain.usecase

import androidx.compose.runtime.snapshots.SnapshotStateList
import callScreen.domain.models.ContactTableItemData
import callScreen.presentation.components.contactTable.ContactTableFilterStore

class GetFilteredContactListUseCase {

    fun execute(
        contactList: SnapshotStateList<ContactTableItemData>,
        filterStore: ContactTableFilterStore
    ): List<ContactTableItemData> {

        val callTableContactListFiltered = mutableListOf<ContactTableItemData>()

        callTableContactListFiltered.addAll(
            contactList.filter {
                isFieldContainsFilterText(it.surname, filterStore.surnameFilterText.value)
                        && isFieldContainsFilterText(it.name, filterStore.nameFilterText.value)
                        && isFieldContainsFilterText(it.patronymic, filterStore.patronymicFilterText.value)
                        && isFieldContainsFilterText(it.phoneNumber, filterStore.numberFilterText.value)
                        && isSexIsTheSame(it, filterStore)
                        && (isAgeFieldsAreEmpty(filterStore)
                        || isAgeBiggerThanMinAgeAndSmallerThanMaxAge(it, filterStore)
                        || (isOnlyMinAgeIsExists(filterStore) && isAgeBiggerThanMinAge(it, filterStore))
                        || (isOnlyMaxAgeIsExists(filterStore) && isAgeSmallerThanMaxAge(it, filterStore)))
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
    itemData: ContactTableItemData,
    filterStore: ContactTableFilterStore
): Boolean {
    if (filterStore.sexFilterSelector.value == null)
        return true

    return itemData.sex == filterStore.sexFilterSelector.value
}

private fun isAgeFieldsAreEmpty(filterStore: ContactTableFilterStore): Boolean =
    filterStore.ageMinFilterText.value.isEmpty() && filterStore.ageMaxFilterText.value.isEmpty()

private fun isAgeBiggerThanMinAgeAndSmallerThanMaxAge(
    itemData: ContactTableItemData,
    filterStore: ContactTableFilterStore
): Boolean = isAgeBiggerThanMinAge(itemData, filterStore) && isAgeSmallerThanMaxAge(itemData, filterStore)

private fun isAgeBiggerThanMinAge(
    itemData: ContactTableItemData,
    filterStore: ContactTableFilterStore
): Boolean {
    if (filterStore.ageMinFilterText.value.toIntOrNull() == null)
        return true
    if (itemData.age == null)
        return false

    return itemData.age > (filterStore.ageMinFilterText.value.toIntOrNull() ?: 0)
}

private fun isAgeSmallerThanMaxAge(
    itemData: ContactTableItemData,
    filterStore: ContactTableFilterStore
): Boolean {
    if (filterStore.ageMaxFilterText.value.toIntOrNull() == null)
        return true
    if (itemData.age == null)
        return false

    return itemData.age < (filterStore.ageMaxFilterText.value.toIntOrNull() ?: Int.MAX_VALUE)
}

private fun isOnlyMaxAgeIsExists(filterStore: ContactTableFilterStore): Boolean =
    filterStore.ageMinFilterText.value.isEmpty() && filterStore.ageMaxFilterText.value.isNotEmpty()


private fun isOnlyMinAgeIsExists(filterStore: ContactTableFilterStore): Boolean =
    filterStore.ageMinFilterText.value.isNotEmpty() && filterStore.ageMaxFilterText.value.isEmpty()
