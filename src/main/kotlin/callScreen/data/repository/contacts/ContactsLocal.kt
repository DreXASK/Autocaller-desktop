package callScreen.data.repository.contacts

import callScreen.domain.repository.contacts.ContactsDto
import kotlinx.serialization.Serializable

@Serializable
data class ContactsParameterGetLocal(
    val url: String
): ContactsDto.Parameter.Get