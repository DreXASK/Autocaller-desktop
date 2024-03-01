import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import di.modules.viewModelsModule
import org.koin.core.context.startKoin
import ui.screens.CallScreen
import ui.screens.ConnectionScreen
import ui.components.NavigationRail
import ui.screens.ServerScreen
import ui.screenModes.MainScreenModes
import ui.theme.AutocallerClientTheme

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
                    MainScreenModes.Connection ->
                        ConnectionScreen()
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
        initKoin()
        App()
    }
}

fun initKoin() {
    startKoin {
        modules(viewModelsModule)
    }
}
