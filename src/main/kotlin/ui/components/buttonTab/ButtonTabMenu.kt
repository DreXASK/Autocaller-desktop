package ui.components.buttonTab

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Preview
@Composable
fun ButtonTabMenu(
    buttonsDataList: List<ButtonTabData>,
    gridCells: GridCells,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = gridCells,
        modifier = Modifier
    ) {
        items(buttonsDataList) { buttonData ->
            ButtonTab(
                onClick = buttonData.onClick,
                icon = buttonData.icon,
                modifier = buttonData.modifier,
                text = buttonData.text,
            )
        }

    }
}