package serverScreen.domain

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import serverScreen.domain.models.ConnectionRequestsTableItemData
import serverScreen.domain.models.TasksTableItemData

class ConnectionRequestsTable {
    val connectionRequestsList: SnapshotStateList<ConnectionRequestsTableItemData> = mutableStateListOf()

    init {
        connectionRequestsList.addAll(
            listOf(
                ConnectionRequestsTableItemData(
                    "01",
                    "23:01 01.04.2024"
                ),
                ConnectionRequestsTableItemData(
                    "02",
                    "15:27 02.04.2024"
                ),
                ConnectionRequestsTableItemData(
                    "03",
                    "15:28 02.04.2024"
                )
            )
        )
    }
}
