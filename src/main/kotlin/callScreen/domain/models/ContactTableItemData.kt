package callScreen.domain.models

import core.presentation.utils.Sex
import kotlinx.serialization.Serializable

@Serializable
data class ContactTableItemData(
    val surname: String?,
    val name: String?,
    val patronymic: String?,
    val phoneNumber: String,
    val sex: Sex?,
    val age: Int?
)