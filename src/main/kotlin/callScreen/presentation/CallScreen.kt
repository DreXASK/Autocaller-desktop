package callScreen.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import org.koin.java.KoinJavaComponent.inject
import callScreen.presentation.components.contactAdderDialog.ContactAdderDialog
import callScreen.presentation.components.SenderContactsToServerDialog
import core.presentation.components.buttonTab.ButtonTabMenuLazyRow
import callScreen.presentation.components.contactTable.ContactTableUI

@Preview
@Composable
fun CallScreen() {
    val viewModel by inject<CallScreenViewModel>(CallScreenViewModel::class.java)
    var isContactAdderDialogOpen by remember { viewModel.isContactAdderDialogOpen }
    var isSenderContactsToServerDialogOpen by remember { viewModel.isSenderContactsToServerDialogOpen }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier.weight(1f),
            contentAlignment = Alignment.TopCenter
        ) {
            ContactTableUI(
                viewModel.contactTable.contactListFiltered,
                viewModel.contactTable.filterStore,
                contentPadding = PaddingValues(30.dp),
                viewModel.contactTable::updateContactListFiltered
            )
        }
        Divider()
        ButtonTabMenuLazyRow(viewModel.buttonsDataList)
    }

    if(isContactAdderDialogOpen) {
        ContactAdderDialog(
            onDismissRequest = { isContactAdderDialogOpen = false },
            addButtonCallback = viewModel.contactTable::addContactToTable
        )
    }

    if(isSenderContactsToServerDialogOpen) {
        SenderContactsToServerDialog(
            onDismissRequest = { isSenderContactsToServerDialogOpen = false },
            buttonCallback = { println("Отправлено") }
        )
    }
}