package core.presentation.components.buttonTab

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

data class ButtonTabData(
    val onClick: () -> Unit,
    val icon: ImageVector,
    val modifier: Modifier = Modifier,
    val iconDescription: String = "",
    val text: String? = null
)
