package ui.components

import ui.screenModes.MainScreenModes
import Res
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*

enum class Page(val title: MutableState<String>) {
    CALL_MANAGER(Res.str.call_manager_label),
    SERVER_ADMIN_MENU(Res.str.server_label),
}

@Preview
@Composable
fun NavigationRail(mode: MutableState<MainScreenModes>) {
    var selectedItem by remember { mutableStateOf(0) }
    val pages = Page.entries.toTypedArray()
    val icons = listOf(Icons.Filled.Home, Icons.Filled.Search)
    NavigationRail {
        pages.forEachIndexed { index, item ->
            when (item) {
                Page.CALL_MANAGER -> {
                    NavigationRailItem(
                        label = { Text(item.title.value)  },
                        icon = { Icon(icons[index], contentDescription = "") },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            mode.value = MainScreenModes.Calls
                        },
                        alwaysShowLabel = false
                    )
                }

                Page.SERVER_ADMIN_MENU -> {
                    NavigationRailItem(
                        label = { Text(item.title.value) },
                        icon = { Icon(icons[index], contentDescription = "") },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            mode.value = MainScreenModes.Server
                        },
                        alwaysShowLabel = false
                    )
                }
            }
        }
    }
}
