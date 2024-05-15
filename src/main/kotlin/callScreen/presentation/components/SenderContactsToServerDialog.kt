package callScreen.presentation.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import callScreen.presentation.CallScreenViewModel
import core.domain.models.MessageTemplateData
import core.presentation.components.MessageTemplatePlaceholdersUi

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun SenderContactsToServerDialog(
    onDismissRequest: () -> Unit,
    viewModel: CallScreenViewModel,
    buttonCallback: (MessageTemplateData) -> Unit
) {

    var dropdownMenuSelected: MessageTemplateData? by remember { mutableStateOf(null) }
    var dropdownMenuExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.messageTemplateService.clearMessageTemplates()
        viewModel.getMessageTemplatesFromServer()
    }

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

                Row(Modifier.height(IntrinsicSize.Max)) {
                    ExposedDropdownMenuBox(
                        expanded = dropdownMenuExpanded,
                        onExpandedChange = { dropdownMenuExpanded = !dropdownMenuExpanded }
                    ) {
                        OutlinedTextField(
                            value = dropdownMenuSelected?.name ?: "Выберите шаблон",
                            onValueChange = { },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownMenuExpanded) }
                        )

                        ExposedDropdownMenu(
                            expanded = dropdownMenuExpanded,
                            onDismissRequest = { dropdownMenuExpanded = false }
                        ) {
                            viewModel.messageTemplateService.messageTemplateList.forEach { messageTemplate ->
                                DropdownMenuItem(
                                    onClick = {
                                        dropdownMenuExpanded = !dropdownMenuExpanded
                                        dropdownMenuSelected = messageTemplate
                                    }
                                ) {
                                    Text(messageTemplate.name)
                                }
                            }
                        }
                    }

                    OutlinedButton(
                        onClick = {  },
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(start = 5.dp),
                    ) {
                        Icon(Icons.Rounded.Add, "")
                    }
                }

                OutlinedTextField(
                    value = dropdownMenuSelected?.text.orEmpty(),
                    onValueChange = {  },
                    maxLines = 10,
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                )

                dropdownMenuSelected?.placeholders?.let {
                    MessageTemplatePlaceholdersUi(
                        templateFieldText = mutableStateOf(""),
                        templatePlaceholders = mutableStateOf(it),
                        cardModifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        readMode = true
                    )
                }

                Button(
                    onClick = {
                        dropdownMenuSelected?.let {
                            buttonCallback(it)
                            onDismissRequest()
                        }
                    },
                    modifier = Modifier.padding(top = 5.dp)
                ) {
                    Text("Обзвонить отображенный список по шаблону")
                }

            }

        }
    }
}