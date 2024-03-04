package callScreen.domain.usecase

import callScreen.domain.models.ContactTableItemData
import callScreen.domain.repository.ContactRepository
import org.koin.java.KoinJavaComponent.inject

class AddContactsToTableFromFileUseCase {
	private val contactRepository by inject<ContactRepository>(ContactRepository::class.java)

	fun execute(
		contactTableContacts: MutableList<ContactTableItemData>,
		url: String
	) {
		val contactList = contactRepository.getContactList(url)
		contactTableContacts.addAll(contactList)
	}

}