package core.presentation.components.table

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import callScreen.domain.models.ContactTableItemData
import core.presentation.components.VerticalDivider

@Preview
@Composable
fun TableItem(
    content: @Composable RowScope.() -> Unit
) {
    TableCard(
        shape = RoundedCornerShape(2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(30.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )
    }
}