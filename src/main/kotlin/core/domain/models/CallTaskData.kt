package core.domain.models

import java.time.LocalDateTime
import java.time.OffsetDateTime


data class CallTaskData(
    val id: Long?,
    val surname: String?,
    val name: String?,
    val patronymic: String?,
    val phoneNumber: String,
    val messageText: String,
    val callAttempts: Int,
    val nextCallDateAndTime: LocalDateTime
)
