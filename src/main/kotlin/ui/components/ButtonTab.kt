package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import utils.useNonBreakingSpace

@Preview
@Composable
fun ButtonTab(
    onClick: () -> Unit,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    iconDescription: String = "",
    text: String? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {
            Icon(
                icon,
                iconDescription,
                Modifier.weight(0.8f).fillMaxSize()
            )

            if (text != null) {
                Box(
                    modifier = Modifier.weight(0.2f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Divider()
                        Text(
                            text,
                            textAlign = TextAlign.Center,
                            maxLines = 1,

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
        modifier = Modifier.width(IntrinsicSize.Min).height(IntrinsicSize.Min),
        text = "Text2222222222222222 33333333333".useNonBreakingSpace(),
    )
}

data class ButtonTabData(
    val onClick: () -> Unit,
    val icon: ImageVector,
    val modifier: Modifier = Modifier,
    val iconDescription: String = "",
    val text: String? = null
)