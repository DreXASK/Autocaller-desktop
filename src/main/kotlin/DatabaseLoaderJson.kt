import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*

import androidx.compose.ui.window.AwtWindow
import java.awt.FileDialog
import java.awt.Frame
import java.awt.Window
import java.io.File

class DatabaseLoaderJson {

}

fun readFileDirectlyAsText(fileName: String): String
        = File(fileName).readText(Charsets.UTF_8)

@Composable
fun testing() {
    var isFileChooserOpen by remember { mutableStateOf(false) }
    //var isButtonEnabled by remember { mutableStateOf(true) }

    if (isFileChooserOpen) {
        FileDialog(
            onCloseRequest = {
                println("Result $it")
                println(it?.let { it1 -> readFileDirectlyAsText(it1) })
            }
        )
    }


    Button(onClick = {
        isFileChooserOpen = !isFileChooserOpen
    }) {

    }
}

@Composable
private fun FileDialog(
    parent: Frame? = null,
    onCloseRequest: (result: String?) -> Unit
) = AwtWindow(
    create = {
        object : FileDialog(parent, "Choose a file", LOAD) {
            override fun setVisible(value: Boolean) {
                super.setVisible(value)
                if (value) {
                    onCloseRequest(directory + file)
                }
            }
        }
    },
    dispose = FileDialog::dispose
)