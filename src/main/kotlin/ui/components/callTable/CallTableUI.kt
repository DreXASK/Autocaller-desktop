package ui.components.callTable

import CallTable
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun CallTable.CallTableUI() {
    LazyColumn(
        contentPadding = PaddingValues(30.dp)
    ) {
        stickyHeader {
            CallTableHeader()
        }
        item {
            CallTableFilter()
        }
        items(callTableContactsFiltered) {
            CallTableItem(it)
        }
    }
}