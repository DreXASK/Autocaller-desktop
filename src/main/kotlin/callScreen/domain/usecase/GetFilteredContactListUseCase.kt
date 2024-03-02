package callScreen.domain.usecase

import androidx.compose.runtime.snapshots.SnapshotStateList
import callScreen.domain.*
import callScreen.domain.models.ContactTableItemData

class GetFilteredContactListUseCase {
	fun execute(
		contactList: SnapshotStateList<ContactTableItemData>,
		filterStore: ContactTableFilterStore
	): List<ContactTableItemData> {

		val callTableContactListFiltered = mutableListOf<ContactTableItemData>()

		filterStore.run {
			callTableContactListFiltered.addAll(contactList.filter {
				it.surname.contains(surnameFilterText.value, ignoreCase = true)
						&& it.name.contains(nameFilterText.value, ignoreCase = true)
						&& it.patronymic.contains(patronymicFilterText.value, ignoreCase = true)
						&& it.number.contains(numberFilterText.value, ignoreCase = true)
						&& it.gender.contains(genderFilterText.value, ignoreCase = true)
						&& (isAgeFieldsAreEmpty(this)
						|| isAgeBiggerThanMinAgeAndSmallerThanMaxAge(it, this)
						|| (isOnlyMinAgeIsExists(this) && isAgeBiggerThanMinAge(it, this))
						|| (isOnlyMaxAgeIsExists(this) && isAgeSmallerThanMaxAge(it, this))
						)
			})
		}

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

