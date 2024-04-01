package serverScreen.presentation.components.serverControlPanel.tabs.connectionRequestsWindow.connectionRequestsTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import serverScreen.domain.models.ConnectionRequestsTableItemData

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun ConnectionRequestsTableUI(
    tasksList: SnapshotStateList<ConnectionRequestsTableItemData>,
    contentPadding: PaddingValues
) {
    LazyColumn(
        contentPadding = contentPadding
    ) {
        stickyHeader {
            ConnectionRequestsTableHeader()
        }
        items(tasksList) {
            ConnectionRequestsTableItem(it)
        }
    }
}