package callScreen.domain

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import callScreen.domain.models.ContactTableItemData
import callScreen.domain.usecase.AddContactToTableUseCase
import callScreen.domain.usecase.AddContactsToTableFromFileUseCase
import callScreen.domain.usecase.GetFilteredContactListUseCase
import callScreen.presentation.components.contactTable.ContactTableFilterStore
import org.koin.java.KoinJavaComponent.inject

class ContactTable {
    private val addContactToTableUseCase by inject<AddContactToTableUseCase>(
        AddContactToTableUseCase::class.java
    )
    private val addContactsToTableViaUrlUseCase by inject<AddContactsToTableFromFileUseCase>(
        AddContactsToTableFromFileUseCase::class.java
    )
    private val getFilteredContactListUseCase by inject<GetFilteredContactListUseCase>(
        GetFilteredContactListUseCase::class.java
    )

    val filterStore by inject<ContactTableFilterStore>(ContactTableFilterStore::class.java)
    private val contactList: SnapshotStateList<ContactTableItemData> = mutableStateListOf()
    val contactListFiltered: SnapshotStateList<ContactTableItemData> = mutableStateListOf()

    fun updateContactListFiltered() {
        contactListFiltered.clear()
        contactListFiltered.addAll(
            getFilteredContactListUseCase.execute(
                contactList,
                filterStore
            )
        )
    }

    fun addContactListToTableViaUrl(url: String) {
        addContactsToTableViaUrlUseCase.execute(contactList, url)
        updateContactListFiltered()
    }

    fun addContactToTable(itemData: ContactTableItemData) {
        addContactToTableUseCase.execute(contactList, itemData)
        updateContactListFiltered()
    }
}