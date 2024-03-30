package callScreen.presentation.components.contactTable

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import callScreen.domain.models.ContactTableItemData


data class ContactTableStore(
    val contactList: SnapshotStateList<ContactTableItemData> = mutableStateListOf(),
    val contactListFiltered: SnapshotStateList<ContactTableItemData> = mutableStateListOf()
)


