package callScreen.presentation.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import callScreen.domain.models.ContactTableItemData
import core.presentation.utils.useNonBreakingSpace

@Preview
@Composable
fun ContactAdderDialog(
	onDismissRequest: () -> Unit,
	addButtonCallback: (itemData: ContactTableItemData) -> Unit
) {
	val fieldStates = ContactAdderDialogStates()

	Dialog(
		onDismissRequest = onDismissRequest
	) {
		Card() {
			Column(
				modifier = Modifier.padding(20.dp).width(IntrinsicSize.Min),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				ContactAdderOutlinedTextField(fieldStates.surname) { Text("Фамилия") }
				ContactAdderOutlinedTextField(fieldStates.name) { Text("Имя") }
				ContactAdderOutlinedTextField(fieldStates.patronymic) { Text("Отчество") }
				ContactAdderOutlinedTextField(fieldStates.number) { Text("Номер телефона") }
				ContactAdderOutlinedTextField(fieldStates.gender) { Text("Пол") }
				ContactAdderOutlinedTextField(fieldStates.age) { Text("Возраст") }
				Row() {
					Button(
						onClick = {
							if (!fieldStates.isDataCorrect()) return@Button
							val itemData = createContactTableItemData(fieldStates)
							addButtonCallback(itemData)
							fieldStates.clearStates()
						},
						modifier = Modifier.padding(end = 5.dp)
					) {
						Text("Добавить")
					}
					Button(
						onClick = {
							if (!fieldStates.isDataCorrect()) return@Button
							val itemData = createContactTableItemData(fieldStates)
							addButtonCallback(itemData)
							onDismissRequest()
						},
						modifier = Modifier.padding(start = 5.dp)
					) {
						Text("Добавить и закрыть".useNonBreakingSpace())
					}
				}
			}

		}

	}
}

private class ContactAdderDialogStates(
	var surname: MutableState<String> = mutableStateOf(""),
	var name: MutableState<String> = mutableStateOf(""),
	var patronymic: MutableState<String> = mutableStateOf(""),
	var number: MutableState<String> = mutableStateOf(""),
	var gender: MutableState<String> = mutableStateOf(""),
	var age: MutableState<String> = mutableStateOf("")
) {
	fun isDataCorrect(): Boolean {
		return !(surname.value.isNullOrBlank()
				|| name.value.isNullOrBlank()
				|| patronymic.value.isNullOrBlank()
				|| number.value.toLongOrNull() == null
				|| gender.value.isNullOrBlank()
				|| age.value.toIntOrNull() == null
				)
	}
	fun clearStates() {
		surname.value = ""
		name.value = ""
		patronymic.value = ""
		number.value = ""
		gender.value = ""
		age.value = ""
	}
}

private fun createContactTableItemData(
	states: ContactAdderDialogStates
): ContactTableItemData {
	return ContactTableItemData(
		states.surname.value,
		states.name.value,
		states.patronymic.value,
		states.number.value,
		states.gender.value,
		states.age.value.toInt()
	)
}

@Composable
private fun ContactAdderOutlinedTextField(
	textState: MutableState<String>,
	label: @Composable (() -> Unit)?
) {
	OutlinedTextField(
		value = textState.value,
		onValueChange = {
			textState.value = it
		},
		modifier = Modifier.fillMaxWidth(),
		label = label,
		singleLine = true
	)
}