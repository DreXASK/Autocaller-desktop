package ui.screens

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import CallTable
import CallTableItemData
import androidx.compose.runtime.*
import kotlinx.serialization.json.Json
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.inject
import readFileDirectlyAsText
import ui.components.ContactAdderDialog
import ui.components.FileDialog
import ui.components.buttonTab.ButtonTabMenuLazyRow
import ui.components.callTable.CallTableUI
import viewModels.CallScreenViewModel

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
            CallTable.CallTableUI()
        }
        Divider()
        ButtonTabMenuLazyRow(viewModel.buttonsDataList)
    }

    if(isFilePickerOpen) {
        FileDialog(
            onCloseRequest = {
                println("Result $it")

                if (it == null)
                    return@FileDialog

                try {
                    val callTableItemDataList =
                        Json.decodeFromString<List<CallTableItemData>>(readFileDirectlyAsText(it))
                    CallTable.addListToTable(callTableItemDataList)
                } catch (e: Exception) {
                    println(e.message)
                }

            }
        )
    }

    if(isContactAdderDialogOpen) {
        ContactAdderDialog() {
            isContactAdderDialogOpen = false
            println("123")
        }
    }
}