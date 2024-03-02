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
import callScreen.presentation.components.callTable.CallTableUI

@Preview
@Composable
fun CallScreen() {
    val viewModel by inject<CallScreenViewModel>(CallScreenViewModel::class.java)
    var isFilePickerOpen by remember { viewModel.isFilePickerOpen  }
    var isContactAdderDialogOpen by remember { viewModel.isContactAdderDialogOpen }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier.weight(1f),
            contentAlignment = Alignment.TopCenter
        ) {
            CallTableUI(
                viewModel.callTableStore,
                viewModel.filterStore,
                viewModel::updateContactListFiltered
            )
        }
        Divider()
        ButtonTabMenuLazyRow(viewModel.buttonsDataList)
    }

    if(isFilePickerOpen) {
        MyFileDialog(
            onCloseRequest = {
                it?.let { viewModel.addContactsToListFromJsonFileViaURL(it) }
            }
        )
    }

    if(isContactAdderDialogOpen) {
        ContactAdderDialog {
            isContactAdderDialogOpen = false
            println("123")
        }
    }
}