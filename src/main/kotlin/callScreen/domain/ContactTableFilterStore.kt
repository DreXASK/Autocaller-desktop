package callScreen.domain

import androidx.compose.runtime.mutableStateOf

class ContactTableFilterStore {
	val surnameFilterText = mutableStateOf("")
	val nameFilterText = mutableStateOf("")
	val patronymicFilterText = mutableStateOf("")
	val numberFilterText = mutableStateOf("")
	val genderFilterText = mutableStateOf("")
	val ageMinFilterText = mutableStateOf("")
	val ageMaxFilterText = mutableStateOf("")
}