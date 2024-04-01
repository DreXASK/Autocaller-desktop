package serverScreen.presentation.components.serverControlPanel.tabs.clientsWindow.clientsTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.presentation.components.VerticalDivider
import core.presentation.components.table.TableItem
import serverScreen.domain.models.ClientsTableItemData
import serverScreen.domain.models.ConnectionRequestsTableItemData

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun ClientsTableItem(itemData: ClientsTableItemData) {
    TableItem {
        Text(itemData.id, Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(itemData.name, Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        TooltipArea(
            tooltip = {
                Card(elevation = 20.dp) {
                    Text("Отвязать клиент от сервера")
                }
            },
            modifier = Modifier.weight(0.1f)
        ) {
            OutlinedButton(
                onClick = { },
                shape = RectangleShape
            ) {
                Icon(Icons.Rounded.Close, "Unbind client from server")
            }
        }
    }
}

