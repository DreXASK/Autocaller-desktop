package serverScreen.presentation.components.serverControlPanel.tabs.connectionRequestsWindow.connectionRequestsTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
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

        TooltipArea(
            tooltip = {
                Card(elevation = 20.dp) {
                    Text("Принять запрос на подключение")
                }
            },
            modifier = Modifier.weight(0.1f)
        ) {
            OutlinedButton(
                onClick = { },
                shape = RectangleShape,
                modifier = Modifier.weight(0.1f)
            ) {
                Icon(Icons.Rounded.Done, "Accept request")
            }
        }

        TooltipArea(
            tooltip = {
                Card(elevation = 20.dp) {
                    Text("Отклонить запрос на подключение")
                }
            },
            modifier = Modifier.weight(0.1f)
        ) {
            OutlinedButton(
                onClick = { },
                shape = RectangleShape,
                modifier = Modifier.weight(0.1f)
            ) {
                Icon(Icons.Rounded.Close, "Deny request")
            }
        }
    }
}