package callScreen.presentation

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import callScreen.domain.*
import org.koin.java.KoinJavaComponent.inject
import core.presentation.components.buttonTab.ButtonTabData
import core.utils.useNonBreakingSpace

class CallScreenViewModel {
	val contactTable by inject<ContactTable>(ContactTable::class.java)

	var isFilePickerOpen = mutableStateOf(false)
	var isContactAdderDialogOpen = mutableStateOf(false)

	val buttonsDataList = listOf(
		ButtonTabData(
			onClick = { isContactAdderDialogOpen.value = true },
			icon = Icons.Rounded.Add,
			text = "Добавить контакт".useNonBreakingSpace(),
		),
		ButtonTabData(
			onClick = { isFilePickerOpen.value = !isFilePickerOpen.value },
			icon = Icons.Rounded.List,
			text = "Загрузить базу (Json)".useNonBreakingSpace(),
		),
		ButtonTabData(
			onClick = { println(789) },
			icon = Icons.Rounded.Send,
			text = "Отправить контакты на сервер".useNonBreakingSpace(),
		)
	)
}