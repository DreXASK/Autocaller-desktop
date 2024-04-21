package callScreen.presentation.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import callScreen.domain.models.ContactTableItemData
import core.presentation.PhoneVisualTransformation
import core.presentation.components.PhoneNumberOutlinedTextField
import core.presentation.utils.Constants.PHONE_NUMBER_LENGTH
import core.presentation.utils.Sex
import core.presentation.utils.useNonBreakingSpace

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun ContactAdderDialog(
	onDismissRequest: () -> Unit,
	addButtonCallback: (itemData: ContactTableItemData) -> Unit
) {
	val fieldStates = ContactAdderDialogStates()
	var dropdownMenuExpanded by remember { mutableStateOf(false) }

	Dialog(
		onDismissRequest = onDismissRequest
	) {
		Card {
			Column(
				modifier = Modifier
					.padding(20.dp)
					.width(IntrinsicSize.Min),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				ContactAdderOutlinedTextField(fieldStates.surname) { Text("Фамилия") }
				ContactAdderOutlinedTextField(fieldStates.name) { Text("Имя") }
				ContactAdderOutlinedTextField(fieldStates.patronymic) { Text("Отчество") }

				PhoneNumberOutlinedTextField(
					phoneNumberState = fieldStates.number,
					modifier = Modifier.fillMaxWidth(),
					label = { Text("Номер телефона") }
				)

				ExposedDropdownMenuBox(
					modifier = Modifier.fillMaxWidth(),
					expanded = dropdownMenuExpanded,
					onExpandedChange = { dropdownMenuExpanded = !dropdownMenuExpanded }
				) {

					OutlinedTextField(
						value = fieldStates.sex.value?.initial.orEmpty(),
						onValueChange = { },
						modifier = Modifier.fillMaxWidth(),
						label = { Text("Пол") },
						readOnly = true,
						singleLine = true,
						trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownMenuExpanded) }
					)

					ExposedDropdownMenu(
						expanded = dropdownMenuExpanded,
						onDismissRequest = { dropdownMenuExpanded = false }
					) {
						DropdownMenuItem(
							onClick = {
								dropdownMenuExpanded = !dropdownMenuExpanded
								fieldStates.sex.value = null
							}
						) {
							Text("")
						}
						Sex.entries.forEach { sex ->
							DropdownMenuItem(
								onClick = {
									dropdownMenuExpanded = !dropdownMenuExpanded
									fieldStates.sex.value = sex
								}
							) {
								Text(sex.initial)
							}
						}
					}
				}

				ContactAdderOutlinedTextField(fieldStates.age) { Text("Возраст") }
				Row {
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
	var sex: MutableState<Sex?> = mutableStateOf(null),
	var age: MutableState<String> = mutableStateOf("")
) {
	fun isDataCorrect(): Boolean {
		if (number.value.length != 11)
			return false
		if (number.value.toLongOrNull() == null)
			return false
		return true
	}
	fun clearStates() {
		surname.value = ""
		name.value = ""
		patronymic.value = ""
		number.value = ""
		sex.value = null
		age.value = ""
	}
}

private fun createContactTableItemData(
	states: ContactAdderDialogStates
): ContactTableItemData {
	return ContactTableItemData(
		states.surname.value.ifBlank { null },
		states.name.value.ifBlank { null },
		states.patronymic.value.ifBlank { null },
		states.number.value,
		states.sex.value,
		states.age.value.toIntOrNull()
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