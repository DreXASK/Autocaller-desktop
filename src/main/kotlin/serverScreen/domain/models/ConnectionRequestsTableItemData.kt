package serverScreen.domain.models

import kotlinx.serialization.Serializable
@Serializable
data class ConnectionRequestsTableItemData(
    val id: String,
    val requestArrivalTime: String
)
