package connectionAdjusterScreen.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import connectionAdjusterScreen.data.remote.PostsService
import connectionAdjusterScreen.presentation.components.ConnectingWindow
import connectionAdjusterScreen.presentation.components.DisconnectedWindow
import org.koin.java.KoinJavaComponent.inject
import core.domain.ServerConnectionStatus
import io.ktor.client.call.*
import io.ktor.client.statement.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private val service = PostsService.create()

@Preview
@Composable
fun ConnectionAdjusterScreen() {
    val viewModel by inject<ConnectionAdjusterScreenViewModel>(ConnectionAdjusterScreenViewModel::class.java)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (viewModel.serverConnection.connectionStatus.value) {
            ServerConnectionStatus.Disconnected ->
                DisconnectedWindow()
            ServerConnectionStatus.Connecting ->
                ConnectingWindow()
            ServerConnectionStatus.PendingToken ->
                TODO()
            ServerConnectionStatus.Connected ->
                Text("Подключение успешно")
        }

        CoroutineScope(Dispatchers.Default).launch {
            println(service.getPosts().body<String>())
        }
    }
}