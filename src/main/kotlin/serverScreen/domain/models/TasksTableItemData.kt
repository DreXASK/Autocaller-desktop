package serverScreen.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class TasksTableItemData(
    val surname: String?,
    val name: String?,
    val patronymic: String?,
    val phoneNumber: String,
    val messageTemplate: String
)
