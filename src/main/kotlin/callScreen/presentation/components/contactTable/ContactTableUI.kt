package callScreen.presentation.components.contactTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import callScreen.domain.models.ContactData

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun ContactTableUI(
    contactList: SnapshotStateList<ContactData>,
    filterStore: ContactTableFilterStore,
    contentPadding: PaddingValues,
	onButtonClicked: (index: Int) -> Unit,
    onFilterValueChanged: () -> Unit
) {
	LazyColumn(
		contentPadding = contentPadding
	) {
		stickyHeader {
			ContactTableHeader()
		}
		item {
			ContactTableFilterRow(
                filterStore,
                onFilterValueChanged
			)
			Divider()
		}
		itemsIndexed(contactList) { index, item ->
			ContactTableItem(index = index, itemData = item, buttonCallBack = onButtonClicked)
		}
	}
}