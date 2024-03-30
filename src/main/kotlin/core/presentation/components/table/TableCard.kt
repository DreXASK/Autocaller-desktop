package core.presentation.components.table

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun TableCard(
    modifier: Modifier = Modifier,
    shape: Shape,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        shape = shape,
        border = BorderStroke(
            width = 1.dp,
            brush = Brush.radialGradient(
                listOf(Color.Transparent, MaterialTheme.colors.primary),
                center = Offset.Unspecified,
                radius = 500f
            )
        ),
        elevation = 10.dp,
        content = content
    )
}