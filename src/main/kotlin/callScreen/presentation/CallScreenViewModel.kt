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
import ui.components.buttonTab.ButtonTabData
import utils.useNonBreakingSpace

class CallScreenViewModel {

	val callTableAddContactsUseCase by inject<CallTableAddContactsUseCase>(CallTableAddContactsUseCase::class.java)
	val callTableUpdateFilterUseCase by inject<CallTableUpdateFilterUseCase>(CallTableUpdateFilterUseCase::class.java)
	val callTableStore by inject<CallTableStore>(CallTableStore::class.java)
	val filterStore by inject<CallTableFilterStore>(CallTableFilterStore::class.java)


	var isFilePickerOpen = mutableStateOf(false)
	var isContactAdderDialogOpen = mutableStateOf(false)

	val buttonsDataList = listOf(
		ButtonTabData(
			onClick = {
				isContactAdderDialogOpen.value = true
				/*                callScreen.domain.CallTable.addContactToTable(
									callScreen.domain.CallTableItemData(
										"Крючков",
										"Илья",
										"Николаевич",
										"88005553535",
										"М",
										50
									)
								)*/
			},
			icon = Icons.Rounded.Add,
			modifier = Modifier.width(IntrinsicSize.Min).height(IntrinsicSize.Min).padding(10.dp),
			text = "Добавить контакт".useNonBreakingSpace(),
		),
		ButtonTabData(
			onClick = { isFilePickerOpen.value = !isFilePickerOpen.value },
			icon = Icons.Rounded.List,
			modifier = Modifier.width(IntrinsicSize.Min).height(IntrinsicSize.Min).padding(10.dp),
			text = "Загрузить базу (Json)".useNonBreakingSpace(),
		),
		ButtonTabData(
			onClick = { println(789) },
			icon = Icons.Rounded.Send,
			modifier = Modifier.width(IntrinsicSize.Min).height(IntrinsicSize.Min).padding(10.dp),
			text = "Отправить контакты на сервер".useNonBreakingSpace(),
		)
	)

	fun addContactToTable(itemData: CallTableItemData) {
		callTableAddContactsUseCase.addContactToTable(callTableStore.contactList, itemData)
		updateContactListFiltered()
	}

	fun addContactListToTable(itemDataList: List<CallTableItemData>) {
		callTableAddContactsUseCase.addListToTable(callTableStore.contactList, itemDataList)
		updateContactListFiltered()
	}

	fun updateContactListFiltered() {
		callTableStore.contactListFiltered.clear()
		callTableStore.contactListFiltered.addAll(
			callTableUpdateFilterUseCase.getFilteredContactList(
				callTableStore.contactList,
				filterStore
			)
		)
	}


}