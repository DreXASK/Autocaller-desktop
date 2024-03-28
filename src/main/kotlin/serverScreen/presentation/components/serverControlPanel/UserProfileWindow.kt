package serverScreen.presentation.components.serverControlPanel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.presentation.components.VerticalDivider
import core.utils.useNonBreakingSpace
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.ServerScreenViewModel

@Preview
@Composable
fun UserProfileWindow() {
    val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier.width(IntrinsicSize.Min),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Администратор",
                fontSize = 30.sp,
                maxLines = 1
            )
            Divider()
            OutlinedButton(
                { },
                Modifier.fillMaxWidth()
            ) {
                Text("Сменить пароль".useNonBreakingSpace())
            }

        }
    }
}