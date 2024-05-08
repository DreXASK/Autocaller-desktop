package core.presentation.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun OutlinedButtonWithTextIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String? = null,
    text: String
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier
                .padding(start = 5.dp)
                .align(Alignment.CenterVertically)
        )
    }
}