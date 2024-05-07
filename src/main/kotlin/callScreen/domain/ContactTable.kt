package callScreen.domain

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import callScreen.domain.models.ContactTableItemData
import callScreen.domain.usecase.GetContactListFromFileUseCase
import callScreen.domain.usecase.GetFilteredContactListUseCase
import callScreen.presentation.components.contactTable.ContactTableFilterStore
import callScreen.presentation.utils.component6
import core.domain.Sex
import io.ktor.util.*
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.get
import org.koin.java.KoinJavaComponent.inject
import java.io.File
import java.io.InputStream

class ContactTable {
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

    fun addContactListToTableViaUrl(url: String, getContactListFromFileUseCase: GetContactListFromFileUseCase) {

        contactList.addAll(getContactListFromFileUseCase.execute(url))
        updateContactListFiltered()
    }

    fun addContactToTable(itemData: ContactTableItemData) {
        contactList.add(itemData)
        updateContactListFiltered()
    }
}


