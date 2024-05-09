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
import core.domain.models.MessageTemplateData
import core.domain.models.MessageTemplatePlaceholders
import core.presentation.components.MessageTemplatePlaceholdersUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.ServerControlPanelWindows

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun MessageTemplateWindow() {
    val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

    LaunchedEffect(Unit) {
        loadDataFromServer(viewModel)
    }

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
                    viewModel.messageTemplateService.messageTemplateList.forEach { messageTemplate ->
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
                onClick = {
                    CoroutineScope(Dispatchers.Default).launch {
                        loadDataFromServer(viewModel)
                    }
                },
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
                .padding(top = 10.dp),
            readMode = false
        )

        Row(Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        dropdownMenuSelected?.let { selectedMessageTemplate ->
                            selectedMessageTemplate.id?.let {
                                if (viewModel.removeMessageTemplateFromServer(selectedMessageTemplate.id)) {
                                    val message = MessageTemplateData(
                                        id = null,
                                        name = selectedMessageTemplate.name,
                                        text = templateText,
                                        placeholders = templatePlaceholders
                                    )
                                    if (viewModel.sendMessageTemplateToServer(message))
                                        loadDataFromServer(viewModel)
                                }
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text("Изменить шаблон")
            }
            Spacer(Modifier.width(10.dp))
            OutlinedButton(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        dropdownMenuSelected?.id?.let {
                            if(viewModel.removeMessageTemplateFromServer(it)) {
                                loadDataFromServer(viewModel)
                                clearFields(stateFields)
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text("Удалить шаблон")
            }
        }


    }

    if (isAdderDialogOpen) {
        MessageTemplateAdderDialog(
            onDismissRequest = { isAdderDialogOpen = false },
            viewModel = viewModel,
            addButtonCallback = { itemData, viewModel ->
                if(viewModel.sendMessageTemplateToServer(itemData)) {
                    loadDataFromServer(viewModel)
                    true
                } else false
            },
        )
    }
}

private suspend fun loadDataFromServer(viewModel: ServerScreenViewModel) {
    viewModel.getMessageTemplatesFromServer()?.let {
        viewModel.messageTemplateService.clearMessageTemplates()
        viewModel.messageTemplateService.addMessageTemplates(it)
    }
}

private fun clearFields(stateFields: MessageTemplateStateFields) {
    stateFields.apply {
        templateText.value = ""
        stateFields.templateName.value = ""
        stateFields.templatePlaceholders.value = MessageTemplatePlaceholders()
    }
}