package serverScreen.presentation.components.serverControlPanel.tabs.completedTasksWindow.completedTasksTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.presentation.components.VerticalDivider
import core.presentation.components.table.OutlinedButtonWithIconAndTooltip
import core.presentation.components.table.TableItem
import core.presentation.utils.applyPhoneVisualTransformation
import serverScreen.domain.models.CompletedTaskData

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun CompletedTasksTableItem(itemData: CompletedTaskData) {
    TableItem(
        rowModifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
    ) {
        Text(itemData.surname ?: "", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(itemData.name ?: "", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(itemData.patronymic ?: "", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(itemData.phoneNumber.applyPhoneVisualTransformation(), Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(itemData.messageText, Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        OutlinedButtonWithIconAndTooltip(
            onClick = { },
            tooltip = { Text("Удалить задание") },
            icon = { Icon(Icons.Rounded.Close, "Remove the task") },
            weight = 0.3f
        )
    }
}