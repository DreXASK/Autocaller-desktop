package serverScreen.domain

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import serverScreen.domain.models.ClientsTableItemData
import serverScreen.domain.models.ConnectionRequestsTableItemData


class ClientsTable {
    val clientsList: SnapshotStateList<ClientsTableItemData> = mutableStateListOf()

    init {
        clientsList.addAll(
            listOf(
                ClientsTableItemData(
                    "01",
                    "Первый клиент"
                ),
                ClientsTableItemData(
                    "02",
                    "Второй"
                ),
                ClientsTableItemData(
                    "03",
                    "Третий"
                )
            )
        )
    }
}