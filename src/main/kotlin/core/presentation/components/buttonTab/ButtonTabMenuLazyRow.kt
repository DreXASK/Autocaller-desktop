package core.presentation.components.buttonTab

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ButtonTabMenuLazyRow(
	buttonsDataList: List<ButtonTabData>,
	modifier: Modifier = Modifier
) {
	LazyRow(
		modifier = Modifier
	) {
		items(buttonsDataList) { buttonData ->
			ButtonTab(
				onClick = buttonData.onClick,
				icon = buttonData.icon,
				modifier = Modifier
					.width(IntrinsicSize.Min)
					.height(IntrinsicSize.Min)
					.padding(10.dp)
					.then(buttonData.modifier),
				text = buttonData.text,
			)
		}

	}
}