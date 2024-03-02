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
import callScreen.domain.models.CallTableItemData
import callScreen.domain.usecase.AddContactsToTableUseCase
import callScreen.domain.usecase.GetFilteredContactListUseCase
import kotlinx.serialization.json.Json
import org.koin.java.KoinJavaComponent.inject
import core.presentation.components.buttonTab.ButtonTabData
import core.utils.useNonBreakingSpace
import java.io.File

class CallScreenViewModel {

	private val addContactsToTableUseCase by inject<AddContactsToTableUseCase>(AddContactsToTableUseCase::class.java)
	private val getFilteredContactListUseCase by inject<GetFilteredContactListUseCase>(GetFilteredContactListUseCase::class.java)
	val callTableStore by inject<CallTableStore>(CallTableStore::class.java)
	val filterStore by inject<CallTableFilterStore>(CallTableFilterStore::class.java)

	var isFilePickerOpen = mutableStateOf(false)
	var isContactAdderDialogOpen = mutableStateOf(false)

	val buttonsDataList = listOf(
		ButtonTabData(
			onClick = {
				isContactAdderDialogOpen.value = true
				/*                callScreen.domain.CallTable.addContactToTable(
									callScreen.domain.models.CallTableItemData(
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

	private fun addContactToTable(itemData: CallTableItemData) {
		addContactsToTableUseCase.execute(callTableStore.contactList, itemData)
		updateContactListFiltered()
	}

	private fun addContactListToTable(itemDataList: List<CallTableItemData>) {
		addContactsToTableUseCase.execute(callTableStore.contactList, itemDataList)
		updateContactListFiltered()
	}

	fun updateContactListFiltered() {
		callTableStore.contactListFiltered.clear()
		callTableStore.contactListFiltered.addAll(
			getFilteredContactListUseCase.execute(
				callTableStore.contactList,
				filterStore
			)
		)
	}

	fun addContactsToListFromJsonFileViaURL(
		url: String
	) {
		addContactsToTableUseCase.execute(
			callTableStore.contactList,
			Json.decodeFromString<List<CallTableItemData>>(File(url).readText())
		)
		updateContactListFiltered()
	}

	private inline fun <reified T> readFileAndDeserializeList(
		url: String
	): List<T> {
		val callTableItemDataList = try {
			Json.decodeFromString<List<T>>(File(url).readText())
		} catch (e: Exception) {
			throw Exception(e.message)
		}
		return callTableItemDataList
	}

}