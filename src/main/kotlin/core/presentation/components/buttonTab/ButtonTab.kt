package core.presentation.components.buttonTab

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import core.presentation.utils.useNonBreakingSpace

@Preview
@Composable
fun ButtonTab(
    onClick: () -> Unit,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    iconDescription: String? = null,
    text: String? = null
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = iconDescription,
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxSize()
            )

            if (text != null) {
                Box(
                    modifier = Modifier.weight(0.2f),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Divider()
                        Text(
                            text,
                            textAlign = TextAlign.Center
                        )
                    }

                }
            }
        }

    }
}

@Preview
@Composable
private fun ButtonTabPreview() {
    ButtonTab(
        onClick = { },
        icon = Icons.Filled.Edit,
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max),
        text = "Text2222222222222222 33333333333".useNonBreakingSpace(),
    )
}