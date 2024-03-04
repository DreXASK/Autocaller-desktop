package callScreen.domain.usecase

import callScreen.domain.models.ContactTableItemData

class AddContactToTableUseCase {
	fun execute(
		callTableContacts: MutableList<ContactTableItemData>,
		itemData: ContactTableItemData
	) {
		callTableContacts.add(itemData)
	}
}