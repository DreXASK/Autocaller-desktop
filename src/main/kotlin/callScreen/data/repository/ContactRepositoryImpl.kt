package callScreen.data.repository

import callScreen.domain.models.ContactTableItemData
import callScreen.domain.repository.ContactRepository
import kotlinx.serialization.json.Json
import java.io.File

class ContactRepositoryImpl : ContactRepository {
    override fun getContactList(url: String): List<ContactTableItemData> {
        return try {
            Json.decodeFromString<List<ContactTableItemData>>(File(url).readText())
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    override fun sendContactListAsJsonToServer() {
        TODO("Not yet implemented")
    }

}