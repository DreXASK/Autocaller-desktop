package callScreen.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.mutableStateOf
import callScreen.domain.*
import callScreen.domain.usecase.GetContactListFromFileUseCase
import core.presentation.CustomFileDialog
import org.koin.java.KoinJavaComponent.inject
import core.presentation.components.buttonTab.ButtonTabData
import core.presentation.utils.useNonBreakingSpace
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.get


class CallScreenViewModel {
    val contactTable by inject<ContactTable>(ContactTable::class.java)

    var isContactAdderDialogOpen = mutableStateOf(false)
    var isSenderContactsToServerDialogOpen = mutableStateOf(false)
    var isLoadingFileTypePickerDialogOpen = mutableStateOf(false)

    val buttonsDataList = listOf(
        ButtonTabData(
            onClick = { isContactAdderDialogOpen.value = true },
            icon = Icons.Rounded.Add,
            text = "Добавить контакт".useNonBreakingSpace(),
        ),
        ButtonTabData(
            onClick = { isLoadingFileTypePickerDialogOpen.value = true },
            icon = Icons.Rounded.List,
            text = "Загрузить список контактов".useNonBreakingSpace(),
        ),
        ButtonTabData(
            onClick = { isSenderContactsToServerDialogOpen.value = true },
            icon = Icons.Rounded.Send,
            text = "Обзвонить список".useNonBreakingSpace(),
        )
    )

    fun loadCsvFile() {
        val filePath = CustomFileDialog.getFilePath()
        filePath?.let {
            contactTable.addContactListToTableViaUrl(
                it,
                get(GetContactListFromFileUseCase::class.java, qualifier = named("CSV"))
            )
        }
    }

    fun loadJsonFile() {
        val filePath = CustomFileDialog.getFilePath()
        filePath?.let {
            contactTable.addContactListToTableViaUrl(
                it,
                get(GetContactListFromFileUseCase::class.java, qualifier = named("JSON"))
            )
        }
    }
}
