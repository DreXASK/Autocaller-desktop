package callScreen.presentation.components.contactAdderDialog

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import callScreen.domain.models.ContactData
import core.domain.utils.Result
import core.presentation.components.PhoneNumberOutlinedTextField
import core.domain.utils.Sex
import core.presentation.utils.useNonBreakingSpace

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun ContactAdderDialog(
    onDismissRequest: () -> Unit,
    addButtonCallback: (itemData: ContactData) -> Unit
) {
    val fieldStates = ContactAdderDialogStates()
    val resultMessageState = mutableStateOf("")
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
                    label = { Text("Номер телефона*") }
                )

                ExposedDropdownMenuBox(
                    modifier = Modifier.fillMaxWidth(),
                    expanded = dropdownMenuExpanded,
                    onExpandedChange = { dropdownMenuExpanded = !dropdownMenuExpanded }
                ) {
                    OutlinedTextField(
                        value = when (fieldStates.sex.value) {
                            Sex.MALE -> "М"
                            Sex.FEMALE -> "Ж"
                            else -> ""
                        },
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
                                Text(when (sex) {
                                    Sex.MALE -> "М"
                                    Sex.FEMALE -> "Ж"
                                })
                            }
                        }
                    }
                }

                ContactAdderOutlinedTextField(fieldStates.age) { Text("Возраст") }

                Row {
                    OutlinedButton(
                        onClick = {
                            val isDataCorrect = fieldStates.isDataCorrect()
                            if (isDataCorrect is Result.Error) {
                                displayErrorToUser(isDataCorrect, resultMessageState)
                                return@OutlinedButton
                            }
                            val itemData = createContactTableItemData(fieldStates)
                            addButtonCallback(itemData)
                            displaySuccessToUser(itemData, resultMessageState)
                            fieldStates.clearStates()
                        },
                        modifier = Modifier.padding(end = 5.dp)
                    ) {
                        Text("Добавить")
                    }

                    OutlinedButton(
                        onClick = {
                            val result = fieldStates.isDataCorrect()
                            if (result is Result.Error) {
                                displayErrorToUser(result, resultMessageState)
                                return@OutlinedButton
                            }
                            val itemData = createContactTableItemData(fieldStates)
                            addButtonCallback(itemData)
                            onDismissRequest()
                        },
                        modifier = Modifier.padding(start = 5.dp)
                    ) {
                        Text("Добавить и закрыть".useNonBreakingSpace())
                    }
                }

                Text(resultMessageState.value, textAlign = TextAlign.Center)

            }

        }

    }
}

private fun displaySuccessToUser(itemData: ContactData, resultMessageState: MutableState<String>) {
    val stringBuilder = StringBuilder()

    stringBuilder.append("Контакт ")

    if (itemData.surname != null) {
        stringBuilder.append("${itemData.surname} ")
    }
    if (itemData.name != null) {
        stringBuilder.append("${itemData.name} ")
    }
    if (itemData.patronymic != null) {
        stringBuilder.append("${itemData.patronymic} ")
    }

    stringBuilder.append("с номером ${itemData.phoneNumber} успешно добавлен.")

    resultMessageState.value = stringBuilder.toString()
}

private fun displayErrorToUser(
    error: Result.Error<Unit, ContactAdderDialogError>,
    resultMessageState: MutableState<String>
) {
    when (error.error) {
        ContactAdderDialogError.PHONE_NUMBER_IS_NULL -> {
            resultMessageState.value = "Номер телефона пуст"
        }

        ContactAdderDialogError.PHONE_NUMBER_IS_NOT_11_DIGITS_LENGTH -> {
            resultMessageState.value = "Длина номера телефона должна быть равна 11 символам"
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

    fun isDataCorrect(): Result<Unit, ContactAdderDialogError> {
        if (number.value.toLongOrNull() == null)
            return Result.Error(ContactAdderDialogError.PHONE_NUMBER_IS_NULL)
        if (number.value.length != 11)
            return Result.Error(ContactAdderDialogError.PHONE_NUMBER_IS_NOT_11_DIGITS_LENGTH)
        return Result.Success(Unit)
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
): ContactData {
    return ContactData(
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