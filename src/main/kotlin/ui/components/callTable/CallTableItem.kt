package ui.components.callTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import CallTable
import CallTableItemData
import androidx.compose.ui.Alignment
import ui.components.VerticalDivider

@Preview
@Composable
fun CallTableItem(callTableItemData: CallTableItemData) {
    Card(
        shape = RoundedCornerShape(2.dp),
        elevation = 10.dp,
        border = BorderStroke(
            1.dp,
            Brush.radialGradient(
                listOf(Color.Transparent, MaterialTheme.colors.primary),
                center = Offset.Unspecified,
                radius = 500f
            )
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(30.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(callTableItemData.surname, Modifier.weight(1f), textAlign = TextAlign.Center)
            VerticalDivider()
            Text(callTableItemData.name, Modifier.weight(1f), textAlign = TextAlign.Center)
            VerticalDivider()
            Text(callTableItemData.patronymic, Modifier.weight(1f), textAlign = TextAlign.Center)
            VerticalDivider()
            Text(callTableItemData.number, Modifier.weight(1f), textAlign = TextAlign.Center)
            VerticalDivider()
            Text(callTableItemData.gender, Modifier.weight(1f), textAlign = TextAlign.Center)
            VerticalDivider()
            Text(callTableItemData.age.toString(), Modifier.weight(1f), textAlign = TextAlign.Center)
        }
    }

}

