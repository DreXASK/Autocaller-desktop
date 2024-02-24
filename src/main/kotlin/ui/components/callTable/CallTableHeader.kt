package ui.components.callTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ui.components.VerticalDivider

@Preview
@Composable
fun CallTableHeader() {
    Card(
        shape = RectangleShape,
        backgroundColor = Color.DarkGray, ////////////////////////////////////////////// Temporary
        border = BorderStroke(
            1.dp,
            Brush.radialGradient(
                listOf(Color.Transparent, MaterialTheme.colors.primary),
                center = Offset.Unspecified,
                radius = 500f
            )
        ),
        elevation = 10.dp,
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
