package core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import core.domain.ServerConnectionStatus

@Composable
fun OutLinedButtonWithProgress(
	onClick: () -> Unit,
	buttonText: @Composable () -> Unit,
) {
	Box(
		contentAlignment = Alignment.Center
	) {
		OutlinedButton(
			onClick = onClick
		) {
			buttonText()
		}
		CircularProgressIndicator(
			modifier = Modifier.width(256.dp).aspectRatio(1f),
			color = MaterialTheme.colors.secondary,
			strokeCap = StrokeCap.Round
		)
	}
}
