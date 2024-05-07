package callScreen.presentation.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import callScreen.presentation.CallScreenViewModel
import org.koin.java.KoinJavaComponent.inject

@Preview
@Composable
fun LoadingFileTypePickerDialog(onDismissRequest: () -> Unit) {

    val viewModel by remember { inject<CallScreenViewModel>(CallScreenViewModel::class.java) }

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .width(IntrinsicSize.Max),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Выберите тип загружаемого файла",
                    modifier = Modifier.padding(bottom = 10.dp),
                    fontSize = 20.sp
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = {
                            viewModel.loadJsonFile()
                            onDismissRequest()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .weight(1f)
                    ) {
                        Text("JSON")
                    }
                    Spacer(Modifier.width(10.dp))
                    OutlinedButton(
                        onClick = {
                            viewModel.loadCsvFile()
                            onDismissRequest()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .weight(1f)
                    ) {
                        Text("CSV")
                    }

                }
            }
        }
    }
}