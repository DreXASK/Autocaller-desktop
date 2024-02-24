package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import network.Connection

@Preview
@Composable
fun ConnectionAdjuster() {

    val ipTextValue = mutableStateOf("")

    Column(
        modifier = Modifier.width(IntrinsicSize.Min),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = ipTextValue.value,
            onValueChange = { ipTextValue.value = it },
            label = { Text("IP:Port") }
        )
        OutlinedButton(
            { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Кнопка")
        }
        Text(Connection.connectionStatus.value.text)
    }
}
