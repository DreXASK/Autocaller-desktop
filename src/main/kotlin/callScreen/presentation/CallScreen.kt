package callScreen.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import org.koin.java.KoinJavaComponent.inject
import callScreen.presentation.components.ContactAdderDialog
import core.presentation.components.MyFileDialog
import core.presentation.components.buttonTab.ButtonTabMenuLazyRow
import callScreen.presentation.components.contactTable.ContactTableUI

@Preview
@Composable
fun CallScreen() {
    val viewModel by inject<CallScreenViewModel>(CallScreenViewModel::class.java)
    val isFilePickerOpen by remember { viewModel.isFilePickerOpen  }
    var isContactAdderDialogOpen by remember { viewModel.isContactAdderDialogOpen }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier.weight(1f),
            contentAlignment = Alignment.TopCenter
        ) {
            ContactTableUI(
                viewModel.contactTable.contactTableStore,
                viewModel.contactTable.filterStore,
                viewModel.contactTable::updateContactListFiltered
            )
        }
        Divider()
        ButtonTabMenuLazyRow(viewModel.buttonsDataList)
    }

    if(isFilePickerOpen) {
        MyFileDialog(
            onCloseRequest = {
                it?.let { viewModel.contactTable.addContactListToTableViaUrl(it) }
            }
        )
    }

    if(isContactAdderDialogOpen) {
        ContactAdderDialog(
            onDismissRequest = { isContactAdderDialogOpen = false },
            addButtonCallback = viewModel.contactTable::addContactToTable
        )
    }
}