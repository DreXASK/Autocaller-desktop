package callScreen.domain.usecase

import callScreen.domain.models.ContactTableItemData
import callScreen.domain.repository.ContactRepository

class GetContactListFromFileUseCase(private val contactRepository: ContactRepository) {

	fun execute(url: String): List<ContactTableItemData> {
		return contactRepository.getContactList(url)
	}

}