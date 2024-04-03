package serverScreen.presentation.components.serverControlPanel.tabs.messageTemplatesWindow

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import serverScreen.domain.models.MessageTemplateData

@Preview
@Composable
fun MessageTemplateAdderDialog(
    onDismissRequest: () -> Unit,
    addButtonCallback: (itemData: MessageTemplateData) -> Unit
) {
    val fieldStates = MessageTemplateAdderDialogStates()

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card() {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .width(500.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = fieldStates.name.value,
                    onValueChange = {
                        fieldStates.name.value = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Имя шаблона") },
                    singleLine = true
                )

                OutlinedTextField(
                    value = fieldStates.text.value,
                    onValueChange = {
                        fieldStates.text.value = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Текст шаблона") },
                    maxLines = 10
                )


                Button(
                    onClick = {
                        if (!fieldStates.isDataCorrect()) return@Button
                        val itemData = MessageTemplateData(
                            fieldStates.name.value,
                            fieldStates.text.value
                        )
                        addButtonCallback(itemData)
                        fieldStates.clearStates()
                        onDismissRequest()
                    },
                    modifier = Modifier.padding(top = 5.dp)
                ) {
                    Text("Добавить")
                }

            }

        }

    }
}

private data class MessageTemplateAdderDialogStates(
    var name: MutableState<String> = mutableStateOf(""),
    var text: MutableState<String> = mutableStateOf(""),
) {
    fun isDataCorrect(): Boolean {
        return (name.value.isNotEmpty() && text.value.isNotEmpty())
    }

    fun clearStates() {
        name.value = ""
        text.value = ""
    }
}