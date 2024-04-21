package callScreen.presentation.components.contactTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import core.presentation.components.VerticalDivider
import core.presentation.components.table.TableFilterRow
import core.presentation.components.table.TableFilterOutlinedTextField

@Preview
@Composable
fun ContactTableFilterRow(
    filterStore: ContactTableFilterStore,
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
            TableFilterOutlinedTextField(numberFilterText, modifier, onFilterValueChange)
            VerticalDivider()
            TableFilterOutlinedTextField(genderFilterText, modifier, onFilterValueChange)
            VerticalDivider()
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TableFilterOutlinedTextField(ageMinFilterText, modifier, onFilterValueChange)
                Text("< X <", fontSize = 16.sp)
                TableFilterOutlinedTextField(ageMaxFilterText, modifier, onFilterValueChange)
            }
        }
    }
}