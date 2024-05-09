package serverScreen.presentation.components.serverControlPanel.tabs.completedTasksWindow.completedTasksTable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import core.domain.utils.Sex
import core.presentation.components.PhoneNumberOutlinedTextField
import core.presentation.components.ToggleButton
import core.presentation.components.VerticalDivider
import core.presentation.components.table.TableFilterOutlinedTextField
import core.presentation.components.table.TableFilterRow

@Composable
fun CompletedTasksTableFilterRow(
    filterStore: CompletedTasksTableFilterStore,
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
            TableFilterOutlinedTextField(callAttempts, Modifier.weight(0.5f), onFilterValueChange)
            VerticalDivider()
            ToggleButton(
                onClick = {
                    filterStore.isSmsUsed.value = !filterStore.isSmsUsed.value
                    onFilterValueChange()
                },
                isToggled = filterStore.isSmsUsed.value,
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxHeight(),
                text = "SMS"
            )
            VerticalDivider()
            Spacer(Modifier.weight(1.5f))
            VerticalDivider()
        }
    }
}