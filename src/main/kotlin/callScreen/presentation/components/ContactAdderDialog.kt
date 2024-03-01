package callScreen.presentation.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Preview
@Composable
fun ContactAdderDialog(
    onDismissRequest: () -> Unit
) {
    var mStateSurname = mutableStateOf("")
    var mStateName = mutableStateOf("")
    var mStatePatronymic = mutableStateOf("")
    var mStateNumber = mutableStateOf("")
    var mStateGender = mutableStateOf("")
    var mStateAge = mutableStateOf("")

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card() {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ContactAdderOutlinedTextField(mStateSurname) { Text("Фамилия") }
                ContactAdderOutlinedTextField(mStateName) { Text("Имя") }
                ContactAdderOutlinedTextField(mStatePatronymic) { Text("Отчество") }
                ContactAdderOutlinedTextField(mStateNumber) { Text("Номер телефона") }
                ContactAdderOutlinedTextField(mStateGender) { Text("Пол") }
                ContactAdderOutlinedTextField(mStateAge) { Text("Возраст") }
                Row {
                    Button(
                        onClick = {}
                    ) {
                        Text("Добавить")
                    }
                    Button(
                        onClick = {}
                    ) {
                        Text("Добавить и закрыть")
                    }
                }
            }

        }

    }
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
        label = label,
        singleLine = true
    )
}