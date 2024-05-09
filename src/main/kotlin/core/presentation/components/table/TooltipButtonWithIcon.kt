package core.presentation.components.table

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowScope.OutlinedButtonWithIconAndTooltip(
    onClick: () -> Unit,
    tooltip: @Composable () -> Unit,
    icon: @Composable () -> Unit,
    weight: Float
) {
    TooltipArea(
        tooltip = {
            Card(elevation = 20.dp) {
                tooltip()
            }
        },
        modifier = Modifier.weight(weight)
    ) {
        OutlinedButton(
            onClick = onClick,
            modifier = Modifier
                .fillMaxSize()
                .height(30.dp),
            shape = RectangleShape
        ) {
            icon()
        }
    }
}