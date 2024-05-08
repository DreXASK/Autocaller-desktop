package serverScreen.presentation.components.serverControlPanel.tabs.callTasksWindow.callTasksTable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.presentation.components.PhoneNumberOutlinedTextField
import core.presentation.components.VerticalDivider
import core.presentation.components.table.TableFilterOutlinedTextField
import core.presentation.components.table.TableFilterRow

@Composable
fun CallTasksTableFilterRow(
    filterStore: CallTasksTableFilterStore,
    onFilterValueChange: () -> Unit
) {
    TableFilterRow {
        filterStore.apply {
            TableFilterOutlinedTextField(surnameFilterText, Modifier.weight(1f), onFilterValueChange)
            VerticalDivider()
            TableFilterOutlinedTextField(nameFilterText, Modifier.weight(1f), onFilterValueChange)
            VerticalDivider()
            TableFilterOutlinedTextField(patronymicFilterText, Modifier.weight(1f), onFilterValueChange)
            VerticalDivider()
            PhoneNumberOutlinedTextField(phoneNumberFilterText, Modifier.weight(1f), onValueChange = onFilterValueChange)
            VerticalDivider()
            TableFilterOutlinedTextField(messageTemplateFilterText, Modifier.weight(2f), onFilterValueChange)
            VerticalDivider()
            TableFilterOutlinedTextField(callAttemptsFilterText, Modifier.weight(0.5f), onFilterValueChange)
            VerticalDivider()
            Spacer(Modifier.weight(1f))
            VerticalDivider()
            Spacer(Modifier.weight(0.3f))
            VerticalDivider()
        }
    }
}