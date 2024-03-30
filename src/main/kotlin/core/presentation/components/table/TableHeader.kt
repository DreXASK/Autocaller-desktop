package core.presentation.components.table

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape


@Preview
@Composable
fun TableHeader(
    content: @Composable RowScope.() -> Unit
) {
    TableCard(
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
            content = content
        )
    }
}