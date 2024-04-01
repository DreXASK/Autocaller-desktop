package core.presentation.components

import java.awt.Frame
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileSystemView


class MyFileDialog {
    companion object {
        fun getFilePath(
            title: String = "Выберите файл",
            parent: Frame? = null
        ): String? {
            val fileChooser = JFileChooser(FileSystemView.getFileSystemView())
            fileChooser.currentDirectory = File(System.getProperty("user.dir"))
            fileChooser.dialogTitle = title
            fileChooser.fileSelectionMode = JFileChooser.FILES_ONLY
            fileChooser.isAcceptAllFileFilterUsed = true
            fileChooser.selectedFile = null
            //fileChooser.currentDirectory = null

            return if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                fileChooser.selectedFile.toString()
            } else null
        }
    }
}


/*) = AwtWindow(
    create = {
        object : FileDialog(parent, title, LOAD) {
            override fun setVisible(value: Boolean) {
                super.setVisible(value)
                if (value) {
                    onCloseRequest(directory + file)
                }
            }
        }
    },
    dispose = FileDialog::dispose
) */
