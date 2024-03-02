package callScreen.presentation.components.contactTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import callScreen.domain.ContactTableFilterStore
import callScreen.domain.ContactTableStore

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun CallTableUI(
	contactTableStore: ContactTableStore,
	filterStore: ContactTableFilterStore,
	onFilterValueChanged: () -> Unit
) {
	LazyColumn(
		contentPadding = PaddingValues(30.dp)
	) {
		stickyHeader {
			CallTableHeader()
		}
		item {
			CallTableFilter(
                filterStore,
                onFilterValueChanged
			)
		}
		items(contactTableStore.contactListFiltered) {
			CallTableItem(it)
		}
	}
}