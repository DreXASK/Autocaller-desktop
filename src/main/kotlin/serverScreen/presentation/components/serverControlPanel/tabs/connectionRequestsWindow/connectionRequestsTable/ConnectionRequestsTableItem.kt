package serverScreen.presentation.components.serverControlPanel.tabs.connectionRequestsWindow.connectionRequestsTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.presentation.components.VerticalDivider
import core.presentation.components.table.OutlinedButtonWithIconAndTooltip
import core.presentation.components.table.TableItem
import serverScreen.domain.models.ConnectionRequestsTableItemData
import serverScreen.domain.models.TasksTableItemData

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun ConnectionRequestsTableItem(itemData: ConnectionRequestsTableItemData) {
    TableItem {
        Text(itemData.id, Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(itemData.requestArrivalTime, Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        OutlinedButtonWithIconAndTooltip(
            tooltip = { Text("Принять запрос на подключение") },
            icon = { Icon(Icons.Rounded.Done, "Accept the request") },
            weight = 0.1f
        ) { }
        OutlinedButtonWithIconAndTooltip(
            tooltip = { Text("Отклонить запрос на подключение") },
            icon = { Icon(Icons.Rounded.Close, "Deny the request") },
            weight = 0.1f
        ) { }
    }
}