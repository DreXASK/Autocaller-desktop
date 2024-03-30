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
                it.surname.contains(filterStore.surnameFilterText.value, ignoreCase = true)
                        && it.name.contains(filterStore.nameFilterText.value, ignoreCase = true)
                        && it.patronymic.contains(filterStore.patronymicFilterText.value, ignoreCase = true)
                        && it.phoneNumber.contains(filterStore.numberFilterText.value, ignoreCase = true)
                        && it.gender.contains(filterStore.genderFilterText.value, ignoreCase = true)
                        && (isAgeFieldsAreEmpty(filterStore)
                        || isAgeBiggerThanMinAgeAndSmallerThanMaxAge(it, filterStore)
                        || (isOnlyMinAgeIsExists(filterStore) && isAgeBiggerThanMinAge(it, filterStore))
                        || (isOnlyMaxAgeIsExists(filterStore) && isAgeSmallerThanMaxAge(it, filterStore))
                        )
            }
        )


        return callTableContactListFiltered
    }
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
) = itemData.age > (filterStore.ageMinFilterText.value.toIntOrNull() ?: 0)

private fun isAgeSmallerThanMaxAge(
    itemData: ContactTableItemData,
    filterStore: ContactTableFilterStore
) = itemData.age < (filterStore.ageMaxFilterText.value.toIntOrNull() ?: Int.MAX_VALUE)

private fun isOnlyMaxAgeIsExists(filterStore: ContactTableFilterStore): Boolean {
    return filterStore.ageMinFilterText.value.isEmpty() &&
            filterStore.ageMaxFilterText.value.isNotEmpty()
}

private fun isOnlyMinAgeIsExists(filterStore: ContactTableFilterStore): Boolean {
    return filterStore.ageMinFilterText.value.isNotEmpty() &&
            filterStore.ageMaxFilterText.value.isEmpty()
}

