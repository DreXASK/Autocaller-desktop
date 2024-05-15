package callScreen.data.repository.contacts

import callScreen.domain.models.ContactData
import callScreen.domain.repository.contacts.ContactRepository
import callScreen.domain.repository.contacts.ContactsTypes
import core.domain.utils.ApiError
import core.domain.utils.Result
import kotlinx.serialization.json.Json
import java.io.File

class ContactRepositoryLocalJSON : ContactRepository {

    override suspend fun getContactList(parameter: ContactsTypes.Parameter.Get): Result<List<ContactData>, ApiError.ContactsError> {
        return try {
            val url = (parameter as ContactsParameterGetLocal).url
            val contactList = Json.decodeFromString<List<ContactData>>(File(url).readText())
            Result.Success(contactList)
        } catch (e: Exception) {
            Result.Error(ApiError.ContactsError.Local.UnknownError(e))
        }
    }

}