package callScreen.domain

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import callScreen.domain.models.ContactData
import callScreen.domain.usecase.GetFilteredContactListUseCase
import callScreen.presentation.components.contactTable.ContactTableFilterStore
import org.koin.java.KoinJavaComponent.inject

class ContactTable {
    private val getFilteredContactListUseCase by inject<GetFilteredContactListUseCase>(
        GetFilteredContactListUseCase::class.java
    )

    val filterStore by inject<ContactTableFilterStore>(ContactTableFilterStore::class.java)
    private val contactList: SnapshotStateList<ContactData> = mutableStateListOf()
    val contactListFiltered: SnapshotStateList<ContactData> = mutableStateListOf()

    fun updateContactListFiltered() {
        contactListFiltered.clear()
        contactListFiltered.addAll(
            getFilteredContactListUseCase.execute(
                contactList,
                filterStore
            )
        )
    }

    fun addContactToTable(contact: ContactData) {
        contactList.add(contact)
    }

    fun addContactListToTable(contacts: List<ContactData>) {
        contactList.addAll(contacts)
    }

    fun clearContactList() {
        contactList.clear()
    }

}


