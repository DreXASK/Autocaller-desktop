package callScreen.domain.usecase

import androidx.compose.runtime.snapshots.SnapshotStateList
import callScreen.domain.*
import callScreen.domain.models.CallTableItemData

class GetFilteredContactListUseCase {
	fun execute(
		contactList: SnapshotStateList<CallTableItemData>,
		filterStore: CallTableFilterStore
	): List<CallTableItemData> {
		val callTableContactListFiltered = mutableListOf<CallTableItemData>()

		filterStore.apply {
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

private fun isAgeFieldsAreEmpty(filterStore: CallTableFilterStore): Boolean =
	filterStore.ageMinFilterText.value.isEmpty() && filterStore.ageMaxFilterText.value.isEmpty()

private fun isAgeBiggerThanMinAgeAndSmallerThanMaxAge(
	itemData: CallTableItemData,
	filterStore: CallTableFilterStore
): Boolean = isAgeBiggerThanMinAge(itemData, filterStore) && isAgeSmallerThanMaxAge(itemData, filterStore)

private fun isAgeBiggerThanMinAge(
	itemData: CallTableItemData,
	filterStore: CallTableFilterStore
) = itemData.age > (filterStore.ageMinFilterText.value.toIntOrNull() ?: 0)

private fun isAgeSmallerThanMaxAge(
	itemData: CallTableItemData,
	filterStore: CallTableFilterStore
) = itemData.age < (filterStore.ageMaxFilterText.value.toIntOrNull() ?: Int.MAX_VALUE)

private fun isOnlyMaxAgeIsExists(filterStore: CallTableFilterStore): Boolean {
	return filterStore.ageMinFilterText.value.isEmpty() &&
			filterStore.ageMaxFilterText.value.isNotEmpty()
}

private fun isOnlyMinAgeIsExists(filterStore: CallTableFilterStore): Boolean {
	return filterStore.ageMinFilterText.value.isNotEmpty() &&
			filterStore.ageMaxFilterText.value.isEmpty()
}

