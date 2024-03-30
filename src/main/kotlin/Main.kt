import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import org.koin.core.context.startKoin
import callScreen.presentation.CallScreen
import core.di.coreModule
import core.presentation.components.NavigationRail
import serverScreen.presentation.ServerScreen
import core.presentation.MainScreenModes
import core.presentation.theme.AutocallerClientTheme
import serverScreen.di.ServerScreenModule
import java.awt.Dimension

@Composable
@Preview
fun App() {
    val mode = mutableStateOf(MainScreenModes.Calls)

    AutocallerClientTheme(isSystemInDarkTheme()) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Row()
            {
                NavigationRail(mode)

                when (mode.value) {
                    MainScreenModes.Calls ->
                        CallScreen()
                    MainScreenModes.Server ->
                        ServerScreen()
                }

            }
        }
    }
}

fun main() = application {
    Window(
        state = WindowState(
            WindowPlacement.Floating,
            size = DpSize(1280.dp,720.dp)
        ),
        title = "Autocaller Client",
        onCloseRequest = ::exitApplication
    ) {
        window.minimumSize = Dimension(1000,400)
        window.iconImage = useResource("drawable/Icon.png", ::loadImageBitmap).toAwtImage()
        initKoin()
        App()
    }
}

fun initKoin() {
    startKoin {
        modules(
            callScreenModule,
            coreModule,
            ServerScreenModule
        )
    }
}
