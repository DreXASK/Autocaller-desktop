package serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.unit.dp
import serverScreen.domain.models.TasksTableItemData


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun TasksTableUI(
    tasksListFiltered: SnapshotStateList<TasksTableItemData>,
    filterStore: TasksTableFilterStore,
    contentPadding: PaddingValues,
    onFilterValueChanged: () -> Unit
) {
    LazyColumn(
        contentPadding = contentPadding
    ) {
        stickyHeader {
            TasksTableHeader()
        }
        item {
            TasksTableFilterRow(
                filterStore,
                onFilterValueChanged
            )
        }
        items(tasksListFiltered) {
            TasksTableItem(it)
        }
    }
}
