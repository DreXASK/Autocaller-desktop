package callScreen.domain

import androidx.compose.runtime.snapshots.SnapshotStateList
import org.koin.java.KoinJavaComponent

class CallTableAddContactsUseCase {

	fun addContactToTable(
		callTableContacts: SnapshotStateList<CallTableItemData>,
		callTableItemData: CallTableItemData
	) {
		callTableContacts.add(callTableItemData)
	}

	fun addListToTable(
		callTableContacts: SnapshotStateList<CallTableItemData>,
		callTableItemDataList: List<CallTableItemData>
	) {
		callTableContacts.addAll(callTableItemDataList)
	}

}