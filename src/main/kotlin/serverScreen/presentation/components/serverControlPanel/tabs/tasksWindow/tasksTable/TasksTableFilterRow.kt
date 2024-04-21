package serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.presentation.components.PhoneNumberOutlinedTextField
import core.presentation.components.VerticalDivider
import core.presentation.components.table.TableFilterOutlinedTextField
import core.presentation.components.table.TableFilterRow

@Composable
fun TasksTableFilterRow(
    filterStore: TasksTableFilterStore,
    onFilterValueChange: () -> Unit
) {
    TableFilterRow {
        val modifier = Modifier.weight(1f)
        filterStore.apply {
            TableFilterOutlinedTextField(surnameFilterText, modifier, onFilterValueChange)
            VerticalDivider()
            TableFilterOutlinedTextField(nameFilterText, modifier, onFilterValueChange)
            VerticalDivider()
            TableFilterOutlinedTextField(patronymicFilterText, modifier, onFilterValueChange)
            VerticalDivider()
            PhoneNumberOutlinedTextField(numberFilterText, modifier, onValueChange = onFilterValueChange)
            VerticalDivider()
            TableFilterOutlinedTextField(messageTemplateFilterText, modifier, onFilterValueChange)
            VerticalDivider()
            Spacer(Modifier.weight(0.3f))
            VerticalDivider()
        }
    }
}