package core.domain.models

import kotlinx.serialization.Serializable


@Serializable
data class CallTaskData(
    val surname: String?,
    val name: String?,
    val patronymic: String?,
    val phoneNumber: String,
    val messageText: String,
    val callAttempts: Int,
    val nextCallDateAndTimeUTC: String
)
