package serverScreen.presentation.components.serverControlPanel.tabs.messageTemplatesWindow

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.presentation.components.OutlinedButtonWithIconText
import org.koin.java.KoinJavaComponent.inject
import serverScreen.domain.models.MessageTemplateData
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.ServerControlPanelWindows

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun MessageTemplatesWindow() {
    val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

    val messageTemplateList = mutableStateListOf(
        MessageTemplateData("Первый шаблон", "Здрасте {surname}, который {name}"),
        MessageTemplateData("Второй шаблон", "А тут пусто.")
    )
    var dropdownMenuSelected: MessageTemplateData? by remember { mutableStateOf(null) }
    var dropdownMenuExpanded by remember { mutableStateOf(false) }

    var templateFieldText by remember { mutableStateOf("") }

    var isAdderDialogOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.width(600.dp).height(IntrinsicSize.Max)
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
                        ServerControlPanelWindows.Tabs
                }

                ExposedDropdownMenuBox(
                    expanded = dropdownMenuExpanded,
                    onExpandedChange = { dropdownMenuExpanded = !dropdownMenuExpanded }
                ) {

                    TextField(
                        value = dropdownMenuSelected?.name.orEmpty(),
                        onValueChange = { },
                        readOnly = true,
                        singleLine = true,
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
                                    templateFieldText = messageTemplate.text
                                }
                            ) {
                                Text(messageTemplate.name)
                            }
                        }
                    }
                }

                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.fillMaxHeight().padding(start = 5.dp),
                ) {
                    Icon(Icons.Rounded.Refresh, "")
                }

                OutlinedButton(
                    onClick = { isAdderDialogOpen = !isAdderDialogOpen },
                    modifier = Modifier.fillMaxHeight().padding(start = 5.dp),
                ) {
                    Icon(Icons.Rounded.Add, "")
                }
            }

            OutlinedTextField(
                value = templateFieldText,
                onValueChange = { templateFieldText = it },
                maxLines = 10,
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
            )

            OutlinedButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Изменить шаблон")
            }
        }
    }

    if(isAdderDialogOpen) {
        MessageTemplateAdderDialog(
            onDismissRequest = { isAdderDialogOpen = false },
            addButtonCallback = { }
        )
    }
}

