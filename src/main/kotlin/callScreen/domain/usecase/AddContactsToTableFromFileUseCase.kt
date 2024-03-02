package callScreen.domain.usecase

import callScreen.domain.models.ContactTableItemData
import callScreen.domain.repository.ContactRepository
import org.koin.java.KoinJavaComponent.inject

class AddContactsToTableFromFileUseCase {
	private val contactRepository by inject<ContactRepository>(ContactRepository::class.java)

	fun execute(
		callTableContacts: MutableList<ContactTableItemData>,
		url: String
	) {
		val contactList = contactRepository.getContactList(url)
		callTableContacts.addAll(contactList)
	}

}