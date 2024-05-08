package callScreen.data.repository.contacts

import callScreen.domain.repository.contacts.ContactsTypes
import kotlinx.serialization.Serializable

@Serializable
data class ContactsParameterGetLocal(
    val url: String
): ContactsTypes.Parameter.Get