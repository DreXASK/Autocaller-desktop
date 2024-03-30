package callScreen.domain

import callScreen.domain.models.ContactTableItemData
import callScreen.domain.usecase.AddContactToTableUseCase
import callScreen.domain.usecase.AddContactsToTableFromFileUseCase
import callScreen.domain.usecase.GetFilteredContactListUseCase
import callScreen.presentation.components.contactTable.ContactTableFilterStore
import callScreen.presentation.components.contactTable.ContactTableStore
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
    val contactTableStore by inject<ContactTableStore>(ContactTableStore::class.java)
    val filterStore by inject<ContactTableFilterStore>(ContactTableFilterStore::class.java)

    fun updateContactListFiltered() {
        contactTableStore.contactListFiltered.clear()
        contactTableStore.contactListFiltered.addAll(
            getFilteredContactListUseCase.execute(
                contactTableStore.contactList,
                filterStore
            )
        )
    }

    fun addContactListToTableViaUrl(url: String) {
        addContactsToTableViaUrlUseCase.execute(contactTableStore.contactList, url)
        updateContactListFiltered()
    }

    fun addContactToTable(itemData: ContactTableItemData) {
        addContactToTableUseCase.execute(contactTableStore.contactList, itemData)
        updateContactListFiltered()
    }
}