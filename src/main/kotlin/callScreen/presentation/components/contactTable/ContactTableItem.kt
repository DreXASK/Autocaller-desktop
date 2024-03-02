package callScreen.presentation.components.contactTable

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
import callScreen.domain.models.ContactTableItemData
import androidx.compose.ui.Alignment
import core.presentation.components.VerticalDivider

@Preview
@Composable
fun CallTableItem(contactTableItemData: ContactTableItemData) {
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
            Text(contactTableItemData.surname, Modifier.weight(1f), textAlign = TextAlign.Center)
            VerticalDivider()
            Text(contactTableItemData.name, Modifier.weight(1f), textAlign = TextAlign.Center)
            VerticalDivider()
            Text(contactTableItemData.patronymic, Modifier.weight(1f), textAlign = TextAlign.Center)
            VerticalDivider()
            Text(contactTableItemData.number, Modifier.weight(1f), textAlign = TextAlign.Center)
            VerticalDivider()
            Text(contactTableItemData.gender, Modifier.weight(1f), textAlign = TextAlign.Center)
            VerticalDivider()
            Text(contactTableItemData.age.toString(), Modifier.weight(1f), textAlign = TextAlign.Center)
        }
    }

}

