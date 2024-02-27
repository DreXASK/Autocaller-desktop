package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import network.Connection

@Preview
@Composable
fun ConnectionAdjuster(ipTextMutableState: MutableState<String>) {

    var ipTextValue by remember { ipTextMutableState }

    Column(
        modifier = Modifier.width(IntrinsicSize.Min),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = ipTextValue,
            onValueChange = { ipTextValue = it },
            label = { Text("IP:Port") }
        )
        Button(
            onClick = {
                println(ipTextMutableState.value)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Подключиться")
        }
        Text(Connection.connectionStatus.value.text)
    }
}
