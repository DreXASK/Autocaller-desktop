package callScreen.domain

import androidx.compose.runtime.snapshots.SnapshotStateList

class CallTableUpdateFilterUseCase {
	fun getFilteredContactList(
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
						&& (isAgeFieldAreEmpty(this)
						|| isAgeBiggerThanMinAgeAndSmallerThanMaxAge(it, this)
						|| isAgeBiggerThanMinAge(it, this)
						|| isAgeSmallerThanMaxAge(it, this)
						)
			})
		}

		return callTableContactListFiltered
	}
}

private fun isAgeFieldAreEmpty(filterStore: CallTableFilterStore): Boolean =
	filterStore.ageMinFilterText.value.isEmpty() && filterStore.ageMaxFilterText.value.isEmpty()


private fun isAgeBiggerThanMinAgeAndSmallerThanMaxAge(
	itemData: CallTableItemData,
	filterStore: CallTableFilterStore
): Boolean = isAgeBiggerThanMinAge(itemData, filterStore) && isAgeSmallerThanMaxAge(itemData, filterStore)

private fun isAgeBiggerThanMinAge(
	itemData: CallTableItemData,
	filterStore: CallTableFilterStore
) = filterStore.ageMinFilterText.value.isNotEmpty()
		&& filterStore.ageMaxFilterText.value.isEmpty()
		&& itemData.age > (filterStore.ageMinFilterText.value.toIntOrNull() ?: 0)

private fun isAgeSmallerThanMaxAge(
	itemData: CallTableItemData,
	filterStore: CallTableFilterStore
) = filterStore.ageMaxFilterText.value.isNotEmpty() &&
		filterStore.ageMinFilterText.value.isEmpty() &&
		itemData.age < (filterStore.ageMaxFilterText.value.toIntOrNull() ?: Int.MAX_VALUE)