package callScreen.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.mutableStateOf
import callScreen.domain.*
import core.presentation.components.MyFileDialog
import org.koin.java.KoinJavaComponent.inject
import core.presentation.components.buttonTab.ButtonTabData
import core.presentation.utils.useNonBreakingSpace


class CallScreenViewModel {
    val contactTable by inject<ContactTable>(ContactTable::class.java)

    var isContactAdderDialogOpen = mutableStateOf(false)
    var isSenderContactsToServerDialogOpen = mutableStateOf(false)

    val buttonsDataList = listOf(
        ButtonTabData(
            onClick = { isContactAdderDialogOpen.value = true },
            icon = Icons.Rounded.Add,
            text = "Добавить контакт".useNonBreakingSpace(),
        ),
        ButtonTabData(
            onClick = {
                val filePath = MyFileDialog.getFilePath()
                filePath?.let {
                    contactTable.addContactListToTableViaUrl(it)
                }
            },
            icon = Icons.Rounded.List,
            text = "Загрузить список контактов (JSON)".useNonBreakingSpace(),
        ),
        ButtonTabData(
            onClick = { isSenderContactsToServerDialogOpen.value = true },
            icon = Icons.Rounded.Send,
            text = "Обзвонить список".useNonBreakingSpace(),
        )
    )
}
