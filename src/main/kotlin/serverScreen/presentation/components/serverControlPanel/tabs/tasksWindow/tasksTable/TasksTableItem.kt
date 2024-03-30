package serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import core.presentation.components.VerticalDivider
import core.presentation.components.table.TableItem
import serverScreen.domain.models.TasksTableItemData

@Preview
@Composable
fun TasksTableItem(itemData: TasksTableItemData) {
    TableItem {
        Text(itemData.surname, Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(itemData.name, Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(itemData.patronymic, Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(itemData.phoneNumber, Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(itemData.messageTemplate, Modifier.weight(1f), textAlign = TextAlign.Center)
    }
}