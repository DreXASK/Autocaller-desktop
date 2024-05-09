package serverScreen.presentation.components.serverControlPanel.tabs.completedTasksWindow.completedTasksTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import core.presentation.components.VerticalDivider
import core.presentation.components.table.TableItem
import core.presentation.utils.applyPhoneVisualTransformation
import serverScreen.domain.models.CompletedTaskData
import java.time.format.DateTimeFormatter

@Preview
@Composable
fun CompletedTasksTableItem(itemData: CompletedTaskData) {

    var isMessageTextExpanded by remember { mutableStateOf(false) }

    TableItem(
        rowModifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .defaultMinSize(minHeight = 30.dp)
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
            text = itemData.messageText,
            modifier = Modifier
                .weight(2f)
                .toggleable(
                    value = isMessageTextExpanded,
                    onValueChange = {
                        isMessageTextExpanded = !isMessageTextExpanded
                    }
                )
            ,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            maxLines = if(isMessageTextExpanded) Int.MAX_VALUE else 1
        )
        VerticalDivider()
        Text(itemData.callAttempts.toString(), Modifier.weight(0.5f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(if(itemData.isSmsUsed) "Отправлено" else "", Modifier.weight(0.8f), textAlign = TextAlign.Center)
        VerticalDivider()
        Text(itemData.informDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), Modifier.weight(1.5f), textAlign = TextAlign.Center)
    }
}