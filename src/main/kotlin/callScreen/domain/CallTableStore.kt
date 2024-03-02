package callScreen.domain

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import callScreen.domain.models.CallTableItemData


class CallTableStore {
    val contactList: SnapshotStateList<CallTableItemData> = mutableStateListOf()
    val contactListFiltered: SnapshotStateList<CallTableItemData> = mutableStateListOf()
}


