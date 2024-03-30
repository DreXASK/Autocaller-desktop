package core.presentation.components.table

import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

@Composable
fun TableFilterOutlinedTextField(
    state: MutableState<String>,
    modifier: Modifier,
    onValueChange: () -> Unit
) {
    OutlinedTextField(
        value = state.value,
        onValueChange = {
            state.value = it
            onValueChange()
        },
        modifier = modifier,
        maxLines = 1
    )
}