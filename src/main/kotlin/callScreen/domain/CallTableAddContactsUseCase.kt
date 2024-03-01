package callScreen.domain

import androidx.compose.runtime.snapshots.SnapshotStateList

class CallTableAddContactsUseCase {

	fun addContactToTable(
		callTableContacts: MutableList<CallTableItemData>,
		callTableItemData: CallTableItemData
	) {
		callTableContacts.add(callTableItemData)
	}

	fun addListToTable(
		callTableContacts: MutableList<CallTableItemData>,
		callTableItemDataList: List<CallTableItemData>
	) {
		callTableContacts.addAll(callTableItemDataList)
	}

}