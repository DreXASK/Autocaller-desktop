package callScreen.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ContactTableItemData(
    val surname: String,
    val name: String,
    val patronymic: String,
    val number: String,
    val gender: String,
    val age: Int
)