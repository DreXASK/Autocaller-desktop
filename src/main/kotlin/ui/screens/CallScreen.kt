package ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import CallTable
import androidx.compose.material.icons.rounded.*
import ui.components.buttonTab.ButtonTabData
import ui.components.buttonTab.ButtonTabMenuRow
import ui.components.callTable.CallTableUI
import utils.useNonBreakingSpace

@Preview
@Composable
fun CallScreen() {
    val buttonsDataList = listOf(
        ButtonTabData(
            onClick = {
                CallTable.addContactToTable(
                    CallTable.CallTableItemData(
                        "Крючков",
                        "Илья",
                        "Николаевич",
                        "88005553535",
                        "М",
                        50
                    )
                )
            },
            icon = Icons.Rounded.Add,
            modifier = Modifier.width(IntrinsicSize.Min).height(IntrinsicSize.Min).padding(10.dp),
            text = "Добавить контакт".useNonBreakingSpace(),
        ),
        ButtonTabData(
            onClick = { println(456) },
            icon = Icons.Rounded.List,
            modifier = Modifier.width(IntrinsicSize.Min).height(IntrinsicSize.Min).padding(10.dp),
            text = "Загрузить базу (Json)".useNonBreakingSpace(),
        ),
        ButtonTabData(
            onClick = { println(789) },
            icon = Icons.Rounded.Send,
            modifier = Modifier.width(IntrinsicSize.Min).height(IntrinsicSize.Min).padding(10.dp),
            text = "Отправить контакты на сервер".useNonBreakingSpace(),
        ),
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier.weight(1f),
            contentAlignment = Alignment.TopCenter
        ) {
            CallTable.CallTableUI()
        }
        Divider()
        ButtonTabMenuRow(
                buttonsDataList
            )

    }
}