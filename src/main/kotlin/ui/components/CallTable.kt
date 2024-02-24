package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.unit.dp


object CallTable {

    private val callTableContacts: SnapshotStateList<CallTableItemData> = mutableStateListOf()

    data class CallTableItemData(
        val surname: String,
        val name: String,
        val patronymic: String,
        val number: String,
        val gender: String,
        val age: Int
    )

    @Preview
    @Composable
    fun CallTable() {
        LazyColumn(
            contentPadding = PaddingValues(30.dp)
        ) {
            item {
                CallTableHeader()
            }
            items(callTableContacts) {
                CallTableItem(it)
            }
        }
    }

    fun addContactToTable(callTableItemData: CallTableItemData) {
        callTableContacts.add(callTableItemData)
    }

}


