package callScreen.presentation.components.callTable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import callScreen.domain.CallTableFilterStore
import core.presentation.components.VerticalDivider

@Preview
@Composable
fun CallTableFilter(
    filterStore: CallTableFilterStore,
    onFilterValueChange: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val modifier = Modifier.weight(1f)
        filterStore.apply {
            FilterOutlinedTextField(surnameFilterText, modifier, onFilterValueChange)
            VerticalDivider()
            FilterOutlinedTextField(nameFilterText, modifier, onFilterValueChange)
            VerticalDivider()
            FilterOutlinedTextField(patronymicFilterText, modifier, onFilterValueChange)
            VerticalDivider()
            FilterOutlinedTextField(numberFilterText, modifier, onFilterValueChange)
            VerticalDivider()
            FilterOutlinedTextField(genderFilterText, modifier, onFilterValueChange)
            VerticalDivider()
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilterOutlinedTextField(ageMinFilterText, modifier, onFilterValueChange)
                Text("< X <", fontSize = 16.sp)
                FilterOutlinedTextField(ageMaxFilterText, modifier, onFilterValueChange)
            }
        }
    }
}

@Composable
fun FilterOutlinedTextField(
    state: MutableState<String>,
    modifier: Modifier,
    onValueChange: () -> Unit
) {
    OutlinedTextField(
        value = state.value,
        onValueChange =  {
            state.value = it
            onValueChange()
        },
        modifier = modifier,
        maxLines = 1
    )
}