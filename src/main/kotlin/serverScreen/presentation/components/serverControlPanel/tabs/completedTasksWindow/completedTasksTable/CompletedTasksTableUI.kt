package serverScreen.presentation.components.serverControlPanel.tabs.completedTasksWindow.completedTasksTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import serverScreen.domain.models.CompletedTasksTableItemData
import serverScreen.domain.models.TasksTableItemData


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun CompletedTasksTableUI(
    completedTasksListFiltered: SnapshotStateList<CompletedTasksTableItemData>,
    filterStore: CompletedTasksTableFilterStore,
    contentPadding: PaddingValues,
    onFilterValueChanged: () -> Unit
) {
    LazyColumn(
        contentPadding = contentPadding
    ) {
        stickyHeader {
            CompletedTasksTableHeader()
        }
        item {
            CompletedTasksTableFilterRow(
                filterStore,
                onFilterValueChanged
            )
        }
        items(completedTasksListFiltered) {
            CompletedTasksTableItem(it)
        }
    }
}
