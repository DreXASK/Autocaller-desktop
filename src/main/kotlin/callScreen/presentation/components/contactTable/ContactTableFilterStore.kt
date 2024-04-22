package callScreen.presentation.components.contactTable

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import core.presentation.utils.Sex

class ContactTableFilterStore(
	val surnameFilterText: MutableState<String> = mutableStateOf(""),
	val nameFilterText: MutableState<String> = mutableStateOf(""),
	val patronymicFilterText: MutableState<String> = mutableStateOf(""),
	val phoneNumberFilterText: MutableState<String> = mutableStateOf(""),
	val sexFilterSelector: MutableState<Sex?> = mutableStateOf(null),
	val ageMinFilterText: MutableState<String> = mutableStateOf(""),
	val ageMaxFilterText: MutableState<String> = mutableStateOf("")
)