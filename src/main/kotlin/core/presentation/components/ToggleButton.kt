package core.presentation.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ToggleButton(
    onClick: () -> Unit,
    modifier: Modifier,
    isToggled: Boolean,
    text: String
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = ButtonDefaults.outlinedButtonColors().contentColor(isToggled).value,
            backgroundColor = ButtonDefaults.outlinedButtonColors()
                .backgroundColor(isToggled).value
        )
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center
        )
    }
}