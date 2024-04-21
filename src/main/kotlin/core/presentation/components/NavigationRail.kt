package core.presentation.components

import core.presentation.MainScreenModes
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import core.presentation.utils.Res


enum class Page(val title: MutableState<String>, val icon: ImageVector) {
    CALL_SCREEN(Res.str.call_screen_label, Icons.Rounded.Call),
    SERVER_ADMIN_MENU(Res.str.server_label, Icons.Rounded.Settings)
}

@Preview
@Composable
fun NavigationRail(mode: MutableState<MainScreenModes>) {
    val pages = Page.entries.toTypedArray()

    NavigationRail(
        modifier = Modifier.width(IntrinsicSize.Min)
    ) {
        pages.forEach {item ->
            when (item) {
                Page.CALL_SCREEN -> {
                    NavigationRailItem(
                        label = { Text(item.title.value, maxLines = 1) },
                        icon = { Icon(item.icon, contentDescription = "") },
                        selected = mode.value == MainScreenModes.Calls,
                        onClick = {
                            mode.value = MainScreenModes.Calls
                        },
                        alwaysShowLabel = false
                    )
                }
                Page.SERVER_ADMIN_MENU -> {
                    NavigationRailItem(
                        label = { Text(item.title.value, maxLines = 1) },
                        icon = { Icon(item.icon, contentDescription = "") },
                        selected = mode.value == MainScreenModes.Server,
                        onClick = {
                            mode.value = MainScreenModes.Server
                        },
                        alwaysShowLabel = false
                    )
                }
            }
        }
    }
}
