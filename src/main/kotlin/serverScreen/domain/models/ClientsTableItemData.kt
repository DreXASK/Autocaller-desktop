package serverScreen.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ClientsTableItemData(
    val id: String,
    val name: String
)
