import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.NavigationRailSample
import ui.theme.AutocallerClientTheme

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    AutocallerClientTheme(isSystemInDarkTheme()) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Row()
            {
                NavigationRailSample()

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            text = "Hello, Desktop!"
                            Res.str.call_manager_label.value = "444"
                        }) {
                        Text(
                            text = text
                        )
                    }
                    Text("Yes")
                }

            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
