package callScreen.presentation.components.contactTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import core.presentation.components.TableCard
import core.presentation.components.VerticalDivider

@Preview
@Composable
fun CallTableHeader() {
    TableCard(
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)
        ) {
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
        }
    }
}
