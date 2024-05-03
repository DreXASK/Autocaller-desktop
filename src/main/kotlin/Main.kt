import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toAwtImage
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import callScreen.di.callScreenModule
import callScreen.presentation.CallScreen
import core.di.coreModule
import core.domain.Result
import core.domain.ServerConnection
import core.domain.ServerConnectionStatus
import core.domain.usecase.GetServerConnectionSettingsUseCase
import core.domain.utils.EncryptionDecryptionUtil
import core.presentation.MainScreenModes
import core.presentation.components.NavigationRail
import core.presentation.theme.AutocallerClientTheme
import kotlinx.coroutines.*
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject
import serverScreen.di.serverScreenModule
import serverScreen.presentation.ServerScreen
import java.awt.Dimension
import java.io.File


@Composable
@Preview
fun App() {
    val mode = mutableStateOf(MainScreenModes.SERVER)

    AutocallerClientTheme(isSystemInDarkTheme()) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Row {
                NavigationRail(mode)

                when (mode.value) {
                    MainScreenModes.CALLS -> CallScreen()
                    MainScreenModes.SERVER -> ServerScreen()
                }
            }
        }
    }
}

fun main() = application {
    Window(
        state = WindowState(
            WindowPlacement.Floating,
            size = DpSize(1280.dp, 720.dp)
        ),
        title = "Autocaller Client",
        onCloseRequest = ::exitApplication
    ) {
        window.minimumSize = Dimension(1000, 400)
        window.iconImage = useResource("drawable/Icon.png", ::loadImageBitmap).toAwtImage()
        initKoin()
        initConnection()
        App()
    }
}

private fun initKoin() {
    startKoin {
        modules(
            callScreenModule,
            coreModule,
            serverScreenModule
        )
    }
}

private fun initConnection() {
    CoroutineScope(Dispatchers.IO).launch {
        val serverConnection by inject<ServerConnection>(ServerConnection::class.java)
        val getServerConnectionSettingsUseCase by inject<GetServerConnectionSettingsUseCase>(
            GetServerConnectionSettingsUseCase::class.java
        )

        delay(500)

        val result = getServerConnectionSettingsUseCase.execute()
        if (result is Result.Success) {
            serverConnection.loginOnServer(result.data.ip, result.data.port, result.data.token)
        }
        else if (result is Result.Error)
            println(result.error)
    }
}