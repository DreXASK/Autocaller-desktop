package serverScreen.presentation.components.serverControlPanel.tabs.callTasksWindow.callTasksTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import core.domain.models.CallTaskData


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun CallTasksTableUI(
    tasksListFiltered: SnapshotStateList<CallTaskData>,
    filterStore: CallTasksTableFilterStore,
    contentPadding: PaddingValues,
    onButtonClicked: (id: Long) -> Unit,
    onFilterValueChanged: () -> Unit
) {
    LazyColumn(
        contentPadding = contentPadding
    ) {
        stickyHeader {
            CallTasksTableHeader()
        }
        item {
            CallTasksTableFilterRow(
                filterStore,
                onFilterValueChanged
            )
        }
        items(tasksListFiltered) {
            CallTasksTableItem(it, onButtonClicked)
        }
    }
}
