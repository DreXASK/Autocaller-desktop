package serverScreen.presentation.components.serverControlPanel.tabs.messageTemplatesWindow

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
import org.koin.java.KoinJavaComponent.inject
import core.domain.models.MessageTemplateData

@Preview
@Composable
fun MessageTemplateAdderDialog(
    onDismissRequest: () -> Unit,
    addButtonCallback: (itemData: MessageTemplateData) -> Unit,
) {
    val stateFields by remember { inject<MessageTemplateStateFields>(MessageTemplateStateFields::class.java) }
    var templateName by remember { stateFields.templateName }
    var templateText by remember { stateFields.templateText }
    val templatePlaceholders by remember { stateFields.templatePlaceholders }

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .width(500.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = templateName,
                    onValueChange = {
                        templateName = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Название шаблона") },
                    singleLine = true
                )

                OutlinedTextField(
                    value = templateText,
                    onValueChange = {
                        templateText = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Текст шаблона") },
                    maxLines = 10
                )

                MessageTemplatePlaceholdersUi(
                    templateFieldText = stateFields.templateText,
                    templatePlaceholders = stateFields.templatePlaceholders,
                    cardModifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                )

                Button(
                    onClick = {
                        if (templateName.isBlank() || templateText.isBlank()) return@Button
                        val itemData = MessageTemplateData(
                            templateName,
                            templateText,
                            templatePlaceholders
                        )
                        addButtonCallback(itemData)
                        onDismissRequest()
                    },
                    modifier = Modifier.fillMaxWidth().padding(top = 5.dp)
                ) {
                    Text("Добавить")
                }

            }

        }

    }
}