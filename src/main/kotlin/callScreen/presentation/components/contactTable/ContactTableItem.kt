package callScreen.presentation.components.contactTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import callScreen.domain.models.ContactData
import core.domain.utils.Sex
import core.presentation.components.VerticalDivider
import core.presentation.components.table.OutlinedButtonWithIconAndTooltip
import core.presentation.components.table.TableItem
import core.presentation.utils.applyPhoneVisualTransformation

@Preview
@Composable
fun ContactTableItem(
    index: Int,
    itemData: ContactData,
    buttonCallBack: (Int) -> Unit
) {
    TableItem(
        rowModifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
    ) {
        Text(itemData.surname ?: "", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(itemData.name ?: "", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(itemData.patronymic ?: "", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(itemData.phoneNumber.applyPhoneVisualTransformation(), Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(
            text = when (itemData.sex) {
                Sex.MALE -> "М"
                Sex.FEMALE -> "Ж"
                null -> ""
            },
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        VerticalDivider()
        Text(
            text = itemData.age?.toString() ?: "",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        OutlinedButtonWithIconAndTooltip(
            onClick = {
                buttonCallBack(index)
            },
            tooltip = { Text("Удалить контакт") },
            icon = { Icon(Icons.Rounded.Close, null) },
            weight = 0.3f
        )
    }
}

