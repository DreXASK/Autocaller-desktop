package core.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import core.presentation.PhoneVisualTransformation
import core.presentation.utils.Constants.PHONE_NUMBER_LENGTH

@Composable
fun PhoneNumberOutlinedTextField(
    phoneNumberState: MutableState<String>,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    onValueChange: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = phoneNumberState.value,
        onValueChange = {
            if (it.length > PHONE_NUMBER_LENGTH)
                return@OutlinedTextField
            if (it.isNotEmpty() && it.lastOrNull()?.isDigit() == false)
                return@OutlinedTextField
            phoneNumberState.value = it
            onValueChange?.invoke()
        },
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        visualTransformation = PhoneVisualTransformation(),
        label = label,
        singleLine = true
    )
}