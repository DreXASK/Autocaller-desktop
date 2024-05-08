package callScreen.data.repository.contacts

import callScreen.domain.models.ContactData
import callScreen.domain.repository.contacts.ContactRepository
import callScreen.domain.repository.contacts.ContactsTypes
import callScreen.presentation.utils.component6
import core.domain.utils.ApiError
import core.domain.utils.Result
import core.domain.utils.Sex
import io.ktor.util.*
import java.io.File

class ContactRepositoryLocalCSV : ContactRepository {

    override suspend fun getContactList(parameter: ContactsTypes.Parameter.Get): Result<List<ContactData>, ApiError.ContactsError> {
        return try {
            val url = (parameter as ContactsParameterGetLocal).url
            val reader = File(url).bufferedReader()
            val header = reader.readLine()
            val contactList = reader.lineSequence()
                .filter { it.isNotBlank() }
                .map {
                    val (surname, name, patronymic, phoneNumber, sex, age) = it.split(
                        ';',
                        ignoreCase = false,
                        limit = 6
                    )
                    ContactData(
                        surname.trim().ifBlank { null },
                        name.trim().ifBlank { null },
                        patronymic.trim().ifBlank { null },
                        phoneNumber.trim().take(11).ifBlank { throw Exception() },
                        sex = when (sex.toLowerCasePreservingASCIIRules()) {
                            "м" -> Sex.MALE
                            "ж" -> Sex.FEMALE
                            else -> null
                        },
                        age.toIntOrNull()
                    )
                }.toList()
            Result.Success(contactList)
        } catch (e: Exception) {
            Result.Error(ApiError.ContactsError.Local.UnknownError(e))
        }
    }

    override suspend fun sendContactList(parameter: ContactsTypes.Parameter.Send): Result<Unit, ApiError.ContactsError> {
        TODO("Not yet implemented")
    }

}