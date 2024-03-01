import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.runtime.*


import callScreen.domain.CallTableItemData
import kotlinx.serialization.json.Json
import ui.components.FileDialog
import java.io.File

class DatabaseLoaderJson {

}

fun readFileDirectlyAsText(fileName: String): String = File(fileName).readText(Charsets.UTF_8)

@Preview
@Composable
fun FilePicker() {

    // Нужно отделить Json от file-picker

    var isFileChooserOpen by remember { mutableStateOf(false) }
    var isButtonEnabled by remember { mutableStateOf(true) }

    if (isFileChooserOpen) {
        FileDialog(
            onCloseRequest = {
                println("Result $it")

                it?.let {
                    try {
                        val callTableItemDataList =
                            Json.decodeFromString<List<CallTableItemData>>(readFileDirectlyAsText(it))
                        CallTable.addListToTable(callTableItemDataList)
                    } catch (e: Exception) {
                        println(e)
                    }
                }

            }
        )
    }

    Button(
        onClick = {
            isFileChooserOpen = !isFileChooserOpen
            isButtonEnabled = false
        },
        enabled = isButtonEnabled
    ) {

    }
}