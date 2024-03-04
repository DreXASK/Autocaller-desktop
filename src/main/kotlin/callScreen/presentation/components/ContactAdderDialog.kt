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
import core.utils.useNonBreakingSpace

@Preview
@Composable
fun ContactAdderDialog(
	onDismissRequest: () -> Unit,
	addButtonCallback: (itemData: ContactTableItemData) -> Unit
) {
	val mFieldStates = ContactAdderDialogStates()
	val mButtonsEnableState = mutableStateOf(false)

	Dialog(
		onDismissRequest = onDismissRequest
	) {
		Card() {
			Column(
				modifier = Modifier.padding(20.dp).width(IntrinsicSize.Min),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				ContactAdderOutlinedTextField(mFieldStates.surname) { Text("Фамилия") }
				ContactAdderOutlinedTextField(mFieldStates.name) { Text("Имя") }
				ContactAdderOutlinedTextField(mFieldStates.patronymic) { Text("Отчество") }
				ContactAdderOutlinedTextField(mFieldStates.number) { Text("Номер телефона") }
				ContactAdderOutlinedTextField(mFieldStates.gender) { Text("Пол") }
				ContactAdderOutlinedTextField(mFieldStates.age) { Text("Возраст") }
				Row() {
					Button(
						onClick = {
							if (!mFieldStates.isDataCorrect()) { return@Button }
							val itemData = createContactTableItemData(mFieldStates)
							addButtonCallback(itemData)
							mFieldStates.clearStates()
						},
						modifier = Modifier.padding(end = 5.dp)
					) {
						Text("Добавить")
					}
					Button(
						onClick = {
							if (!mFieldStates.isDataCorrect()) return@Button
							val itemData = createContactTableItemData(mFieldStates)
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

private data class ContactAdderDialogStates(
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
	mStates: ContactAdderDialogStates
): ContactTableItemData {
	return ContactTableItemData(
		mStates.surname.value,
		mStates.name.value,
		mStates.patronymic.value,
		mStates.number.value,
		mStates.gender.value,
		mStates.age.value.toInt()
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