package callScreen.presentation.components.contactTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import callScreen.domain.models.ContactData
import core.domain.utils.Sex
import core.presentation.components.VerticalDivider
import core.presentation.components.table.TableItem
import core.presentation.utils.applyPhoneVisualTransformation

@Preview
@Composable
fun ContactTableItem(itemData: ContactData) {
    TableItem {
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
    }
}

