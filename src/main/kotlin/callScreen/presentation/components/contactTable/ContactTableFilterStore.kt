package callScreen.presentation.components.contactTable

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class ContactTableFilterStore(
	val surnameFilterText: MutableState<String> = mutableStateOf(""),
	val nameFilterText: MutableState<String> = mutableStateOf(""),
	val patronymicFilterText: MutableState<String> = mutableStateOf(""),
	val numberFilterText: MutableState<String> = mutableStateOf(""),
	val genderFilterText: MutableState<String> = mutableStateOf(""),
	val ageMinFilterText: MutableState<String> = mutableStateOf(""),
	val ageMaxFilterText: MutableState<String> = mutableStateOf("")
)