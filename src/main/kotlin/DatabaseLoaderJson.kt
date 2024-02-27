import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*

import androidx.compose.ui.window.AwtWindow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import ui.components.FileDialog
import java.awt.FileDialog
import java.awt.Frame
import java.awt.Window
import java.io.File

class DatabaseLoaderJson {

}

fun readFileDirectlyAsText(fileName: String): String = File(fileName).readText(Charsets.UTF_8)

@Preview
@Composable
fun testing() {
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

/*    val count =
        Json.decodeFromString<List<CallTableItemData>>(readFileDirectlyAsText("C:\\Users\\DreX\\Desktop\\File.txt"))
            .count()
    println(count)*/

    Button(
        onClick = {
            isFileChooserOpen = !isFileChooserOpen
            isButtonEnabled = false
        },
        enabled = isButtonEnabled
    ) {

    }
}