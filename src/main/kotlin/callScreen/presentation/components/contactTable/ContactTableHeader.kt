package callScreen.presentation.components.contactTable

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
fun ContactTableHeader() {
    TableHeader {
        Text("Фамилия", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text("Имя", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text("Отчество", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text("Номер телефона", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text("Пол", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text("Возраст", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Spacer(Modifier.weight(0.3f))
    }
}
