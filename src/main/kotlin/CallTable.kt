import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList



object CallTable {
    val callTableContacts: SnapshotStateList<CallTableItemData> = mutableStateListOf()
    val callTableContactsFiltered: SnapshotStateList<CallTableItemData> = mutableStateListOf()

    val surnameFilterText = mutableStateOf("")
    val nameFilterText = mutableStateOf("")
    val patronymicFilterText = mutableStateOf("")
    val numberFilterText = mutableStateOf("")
    val genderFilterText = mutableStateOf("")
    val ageMinFilterText = mutableStateOf("")
    val ageMaxFilterText = mutableStateOf("")

    data class CallTableItemData(
        val surname: String,
        val name: String,
        val patronymic: String,
        val number: String,
        val gender: String,
        val age: Int
    )

    fun addContactToTable(callTableItemData: CallTableItemData) {
        callTableContacts.add(callTableItemData)
        updateFilter()
    }

    fun updateFilter() {
        callTableContactsFiltered.clear()
        callTableContactsFiltered.addAll(callTableContacts.filter {
            it.surname.contains(surnameFilterText.value, ignoreCase = true)
                    && it.name.contains(nameFilterText.value, ignoreCase = true)
                    && it.patronymic.contains(patronymicFilterText.value, ignoreCase = true)
                    && it.number.contains(numberFilterText.value, ignoreCase = true)
                    && it.gender.contains(genderFilterText.value, ignoreCase = true)

                    && (
                            (ageMinFilterText.value.isEmpty() && ageMaxFilterText.value.isEmpty()
                            )
                        || (ageMinFilterText.value.isNotEmpty() && ageMaxFilterText.value.isNotEmpty()
                            && it.age > (ageMinFilterText.value.toIntOrNull() ?: 0)
                            && it.age < (ageMaxFilterText.value.toIntOrNull() ?: Int.MAX_VALUE)
                            )
                        || (ageMinFilterText.value.isNotEmpty()
                            && ageMaxFilterText.value.isEmpty()
                            && it.age > (ageMinFilterText.value.toIntOrNull() ?: 0)
                            )
                        || (ageMaxFilterText.value.isNotEmpty() &&
                            ageMinFilterText.value.isEmpty() &&
                            it.age < (ageMaxFilterText.value.toIntOrNull() ?: Int.MAX_VALUE))
                    )

        }
        )
    }
}


