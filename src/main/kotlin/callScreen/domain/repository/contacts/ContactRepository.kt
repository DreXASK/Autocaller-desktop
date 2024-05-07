package callScreen.domain.repository.contacts

import callScreen.domain.models.ContactData
import core.domain.utils.ApiError
import core.domain.utils.Result

interface ContactRepository {
    suspend fun getContactList(parameter: ContactsDto.Parameter.Get): Result<List<ContactData>, ApiError.ContactsError>

    suspend fun sendContactList(parameter: ContactsDto.Parameter.Send): Result<Unit, ApiError.ContactsError>
}