package serverScreen.presentation.components.serverControlPanel.tabs.messageTemplatesWindow

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.presentation.components.OutlinedButtonWithIconText
import org.koin.java.KoinJavaComponent.inject
import serverScreen.domain.models.MessageTemplateData
import serverScreen.domain.models.MessageTemplatePlaceholders
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.ServerControlPanelWindows

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun MessageTemplateWindow() {
    val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

    val messageTemplateList = mutableStateListOf(
        MessageTemplateData(
            "Первый шаблон",
            "Здрасте {Имя}, который {Фамилия}",
            placeholders = MessageTemplatePlaceholders(isSurnamePlaceholderUsed = true, isNamePlaceholderUsed = true)
        ),
        MessageTemplateData(
            name = "Второй шаблон",
            text = "А тут пусто.",
            placeholders = MessageTemplatePlaceholders()
        )
    )
    var dropdownMenuSelected: MessageTemplateData? by remember { mutableStateOf(null) }
    var dropdownMenuExpanded by remember { mutableStateOf(false) }

    val stateFields by remember { inject<MessageTemplateStateFields>(MessageTemplateStateFields::class.java) }
    var templateText by remember { stateFields.templateText }
    var templatePlaceholders by remember { stateFields.templatePlaceholders }

    var isAdderDialogOpen by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(30.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButtonWithIconText(
                modifier = Modifier.fillMaxHeight().padding(end = 5.dp),
                icon = Icons.Rounded.ArrowBack,
                text = "Назад в меню"
            ) {
                viewModel.serverControlPanel.windowToDisplay.value =
                    ServerControlPanelWindows.TABS
            }

            ExposedDropdownMenuBox(
                expanded = dropdownMenuExpanded,
                onExpandedChange = { dropdownMenuExpanded = !dropdownMenuExpanded },
                modifier = Modifier.weight(1f)
            ) {

                OutlinedTextField(
                    value = dropdownMenuSelected?.name.orEmpty(),
                    onValueChange = { },
                    modifier = Modifier
                        .fillMaxWidth(),
                    readOnly = true,
                    singleLine = true,
                    placeholder = { Text("Выберите шаблон") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownMenuExpanded) }
                )

                ExposedDropdownMenu(
                    expanded = dropdownMenuExpanded,
                    onDismissRequest = { dropdownMenuExpanded = false }
                ) {
                    messageTemplateList.forEach { messageTemplate ->
                        DropdownMenuItem(
                            onClick = {
                                dropdownMenuExpanded = !dropdownMenuExpanded
                                dropdownMenuSelected = messageTemplate
                                templateText = messageTemplate.text
                                templatePlaceholders = messageTemplate.placeholders
                            }
                        ) {
                            Text(messageTemplate.name)
                        }
                    }
                }
            }

            OutlinedButton(
                onClick = { },
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 5.dp),
            ) {
                Icon(Icons.Rounded.Refresh, null)
            }

            OutlinedButton(
                onClick = { isAdderDialogOpen = !isAdderDialogOpen },
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 5.dp),
            ) {
                Icon(Icons.Rounded.Add, null)
            }
        }

        OutlinedTextField(
            value = templateText,
            onValueChange = { templateText = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            maxLines = 10,
            label = { Text("Текст шаблона") }
        )

        MessageTemplatePlaceholdersUi(
            templateFieldText = stateFields.templateText,
            templatePlaceholders = stateFields.templatePlaceholders,
            cardModifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )

        OutlinedButton(
            onClick = { println("TODO()") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Изменить шаблон")
        }
    }

    if (isAdderDialogOpen) {
        MessageTemplateAdderDialog(
            onDismissRequest = { isAdderDialogOpen = false },
            addButtonCallback = { },
        )
    }
}


