package serverScreen.presentation.components.serverControlPanel.tabs.callTasksWindow.callTasksTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import core.presentation.components.VerticalDivider
import core.presentation.components.table.OutlinedButtonWithIconAndTooltip
import core.presentation.components.table.TableItem
import core.presentation.utils.applyPhoneVisualTransformation
import core.domain.models.CallTaskData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

@Preview
@Composable
fun CallTasksTableItem(
    itemData: CallTaskData,
    buttonCallBack: suspend (id: Long) -> Boolean)
{
    TableItem(
        rowModifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        Text(itemData.surname ?: "", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(itemData.name ?: "", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(itemData.patronymic ?: "", Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(itemData.phoneNumber.applyPhoneVisualTransformation(), Modifier.weight(1f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(itemData.messageText, Modifier.weight(2f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(itemData.callAttempts.toString(), Modifier.weight(0.5f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(
            itemData.nextCallDateAndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), Modifier.weight(1f), textAlign = TextAlign.Center
        )
        VerticalDivider()
        OutlinedButtonWithIconAndTooltip(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    itemData.id?.let { buttonCallBack(it) }
                        ?: println("Can't remove callTask - id is null").also { println(itemData) }
                }
            },
            tooltip = { Text("Удалить задание") },
            icon = { Icon(Icons.Rounded.Close, "Remove the task") },
            weight = 0.3f
        )
    }
}