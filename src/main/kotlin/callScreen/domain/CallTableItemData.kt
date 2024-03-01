package callScreen.domain

import kotlinx.serialization.Serializable

@Serializable
data class CallTableItemData(
    val surname: String,
    val name: String,
    val patronymic: String,
    val number: String,
    val gender: String,
    val age: Int
)