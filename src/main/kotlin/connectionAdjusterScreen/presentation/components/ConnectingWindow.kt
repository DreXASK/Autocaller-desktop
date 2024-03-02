package connectionAdjusterScreen.presentation.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ConnectingWindow() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        OutlinedButton(
            onClick = {  }
        ) {
            Text("Отмена подключения")
        }
        CircularProgressIndicator(
            modifier = Modifier.width(256.dp).aspectRatio(1f),
            color = MaterialTheme.colors.secondary,
            strokeCap = StrokeCap.Round
        )
    }
}