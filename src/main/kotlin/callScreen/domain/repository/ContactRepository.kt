package callScreen.domain.repository

import callScreen.domain.models.ContactTableItemData

interface ContactRepository {
    fun getContactList(url: String) : List<ContactTableItemData>
    fun sendContactList()
}