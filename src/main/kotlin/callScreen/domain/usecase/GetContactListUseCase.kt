package callScreen.domain.usecase

import callScreen.domain.models.ContactData
import callScreen.domain.repository.contacts.ContactRepository
import callScreen.domain.repository.contacts.ContactsDto
import core.domain.utils.ApiError
import core.domain.utils.Result

class GetContactListUseCase(private val contactRepository: ContactRepository) {

	suspend fun execute(contactReceive: ContactsDto.Parameter.Get): Result<List<ContactData>, ApiError.ContactsError> {
		val result = contactRepository.getContactList(contactReceive)
		return result
	}

}