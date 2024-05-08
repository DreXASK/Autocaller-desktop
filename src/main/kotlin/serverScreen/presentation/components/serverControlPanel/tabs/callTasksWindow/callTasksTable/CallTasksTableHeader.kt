package serverScreen.presentation.components.serverControlPanel.tabs.callTasksWindow.callTasksTable

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
fun CallTasksTableHeader() {
    TableHeader {
        Text("Фамилия", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text("Имя", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text("Отчество", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text("Номер телефона", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text("Сообщение", Modifier.weight(2f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text("Попыток", Modifier.weight(0.5f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text("Время след. звонка", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Spacer(Modifier.weight(0.3f))
    }
}