package callScreen.domain.usecase

import callScreen.domain.models.CallTableItemData

class AddContactsToTableUseCase {
	fun execute(
		callTableContacts: MutableList<CallTableItemData>,
		callTableItemData: CallTableItemData
	) {
		callTableContacts.add(callTableItemData)
	}

	fun execute(
		callTableContacts: MutableList<CallTableItemData>,
		callTableItemDataList: List<CallTableItemData>
	) {
		callTableContacts.addAll(callTableItemDataList)
	}

}