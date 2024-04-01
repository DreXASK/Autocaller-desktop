package serverScreen.presentation.components.serverControlPanel.tabs.connectionRequestsWindow.connectionRequestsTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import core.presentation.components.VerticalDivider
import core.presentation.components.table.TableHeader
@Preview
@Composable
fun ConnectionRequestsTableHeader() {
    TableHeader {
        Text("ID запроса на подключение", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text("Время прихода запроса", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Spacer(Modifier.weight(0.1f))
        VerticalDivider()
        Spacer(Modifier.weight(0.1f))
    }
}