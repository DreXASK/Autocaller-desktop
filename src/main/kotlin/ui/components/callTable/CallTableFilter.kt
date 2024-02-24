package ui.components.callTable

import CallTable
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.components.VerticalDivider

@Preview
@Composable
fun CallTableFilter() {
    Row(
        modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val modifier = Modifier.weight(1f)

        FilterOutlinedTextField(CallTable.surnameFilterText, modifier)
        VerticalDivider()
        FilterOutlinedTextField(CallTable.nameFilterText, modifier)
        VerticalDivider()
        FilterOutlinedTextField(CallTable.patronymicFilterText, modifier)
        VerticalDivider()
        FilterOutlinedTextField(CallTable.numberFilterText, modifier)
        VerticalDivider()
        FilterOutlinedTextField(CallTable.genderFilterText, modifier)
        VerticalDivider()
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilterOutlinedTextField(CallTable.ageMinFilterText, modifier)
            Text("< X <", fontSize = 16.sp)
            FilterOutlinedTextField(CallTable.ageMaxFilterText, modifier)
        }
    }
}

@Composable
fun FilterOutlinedTextField(
    state: MutableState<String>,
    modifier: Modifier
) {
    OutlinedTextField(
        state.value,
        {
            state.value = it
            CallTable.updateFilter()
        },
        modifier,
        maxLines = 1
    )
}