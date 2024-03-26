package serverScreen.presentation.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import core.presentation.components.buttonTab.ButtonTabData
import core.presentation.components.buttonTab.ButtonTabMenuGrid
import core.utils.useNonBreakingSpace

@Preview
@Composable
fun ServerControlPanelWindow() {
	val buttonData = listOf(
		ButtonTabData(
			{ },
			Icons.Rounded.List,
			text = "Кнопка 1".useNonBreakingSpace(),
		),
		ButtonTabData(
			{ },
			Icons.Rounded.Add,
			text = "Кнопка 2".useNonBreakingSpace()
		),
		ButtonTabData(
			{ },
			Icons.Rounded.Edit,
			text = "Кнопка 3".useNonBreakingSpace()
		),
	)

	Column(
		Modifier.fillMaxWidth(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Box(
			contentAlignment = Alignment.Center
		) {
			Text(
				"Панель управления сервером",
				fontSize = 25.sp)
		}
		ButtonTabMenuGrid(
			buttonsDataList = buttonData,
			gridCells = GridCells.Fixed(4)
		)
	}
}