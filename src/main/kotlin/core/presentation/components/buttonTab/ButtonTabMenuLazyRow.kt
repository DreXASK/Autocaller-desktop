package core.presentation.components.buttonTab

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Preview
@Composable
fun ButtonTabMenuLazyRow(
    buttonsDataList: List<ButtonTabData>,
    modifier: Modifier = Modifier
) {
    LazyRow (
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