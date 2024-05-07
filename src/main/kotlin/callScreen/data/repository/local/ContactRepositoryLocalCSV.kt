package callScreen.data.repository.local

import callScreen.domain.models.ContactTableItemData
import callScreen.domain.repository.ContactRepository
import callScreen.presentation.utils.component6
import core.domain.Sex
import io.ktor.util.*
import kotlinx.serialization.json.Json
import java.io.File

class ContactRepositoryLocalCSV : ContactRepository {

    override fun getContactList(url: String): List<ContactTableItemData> {
        return try {
            val reader = File(url).bufferedReader()
            val header = reader.readLine()
            reader.lineSequence()
                .filter { it.isNotBlank() }
                .map {
                    val (surname, name, patronymic, phoneNumber, sex, age) = it.split(
                        ';',
                        ignoreCase = false,
                        limit = 6
                    )
                    ContactTableItemData(
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
        } catch (e: Exception) {
            throw e
        }
    }

    override fun sendContactList() {
        TODO("Not yet implemented")
    }

}