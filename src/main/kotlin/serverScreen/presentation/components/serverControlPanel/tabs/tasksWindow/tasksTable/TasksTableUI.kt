package serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun TasksTableUI(
    tasksStore: TasksTableStore,
    filterStore: TasksTableFilterStore,
    onFilterValueChanged: () -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(30.dp)
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
        items(tasksStore.tasksListFiltered) {
            TasksTableItem(it)
        }
    }
}
