package serverScreen.domain.models

import java.time.LocalDateTime

data class CompletedTaskData(
    val id: Long?,
    val surname: String?,
    val name: String?,
    val patronymic: String?,
    val phoneNumber: String,
    val messageText: String,
    val callAttempts: Int,
    val isSmsUsed: Boolean,
    val informTime: LocalDateTime
)
