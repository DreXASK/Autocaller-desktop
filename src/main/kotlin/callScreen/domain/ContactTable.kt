package callScreen.domain

import callScreen.domain.usecase.AddContactsToTableFromFileUseCase
import callScreen.domain.usecase.GetFilteredContactListUseCase
import org.koin.java.KoinJavaComponent.inject

class ContactTable {
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

    /*fun addContactListToTableViaUrl(
        itemDataList: List<ContactTableItemData>
    ) {
        addContactsToTableUseCase.execute(
            contactTableStore.contactList,
            itemDataList
        )
        updateContactListFiltered()
    }*/
}