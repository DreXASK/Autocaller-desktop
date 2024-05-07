package callScreen.domain.models

import core.domain.utils.Sex
import kotlinx.serialization.Serializable

@Serializable
data class ContactData(
    val surname: String?,
    val name: String?,
    val patronymic: String?,
    val phoneNumber: String,
    val sex: Sex?,
    val age: Int?
)