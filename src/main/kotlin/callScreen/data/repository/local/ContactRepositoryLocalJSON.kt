package callScreen.data.repository.local

import callScreen.domain.models.ContactTableItemData
import callScreen.domain.repository.ContactRepository
import kotlinx.serialization.json.Json
import java.io.File

class ContactRepositoryLocalJSON : ContactRepository {

    override fun getContactList(url: String): List<ContactTableItemData> {
        return try {
            Json.decodeFromString<List<ContactTableItemData>>(File(url).readText())
        } catch (e: Exception) {
            throw e
        }
    }

    override fun sendContactList() {
        TODO("Not yet implemented")
    }

}