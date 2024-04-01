package serverScreen.presentation.components.serverControlPanel.tabs.clientsWindow.clientsTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import serverScreen.domain.models.ClientsTableItemData

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun ClientsTableUI(
    clientsList: SnapshotStateList<ClientsTableItemData>,
    contentPadding: PaddingValues
) {
    LazyColumn(
        contentPadding = contentPadding
    ) {
        stickyHeader {
            ClientsTableHeader()
        }
        items(clientsList) {
            ClientsTableItem(it)
        }
    }
}