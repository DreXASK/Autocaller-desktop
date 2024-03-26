package serverScreen.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import core.domain.AdminAuthorization
import core.domain.AuthStatus
import core.presentation.components.OutLinedButtonWithProgress
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.ServerScreenViewModel

@Composable
fun AuthorizationAttemptWindow() {
	val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

	val abc = CoroutineScope(Dispatchers.Default).launch {
		delay(3000)
		viewModel.auth.authStatus.value = AuthStatus.Authorized
	}

	Box(
		Modifier.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
		OutLinedButtonWithProgress(
			onClick = {
				viewModel.auth.authStatus.value = AuthStatus.Unauthorized
				abc.cancel()
			},
			buttonText = { Text("Отмена авторизации") }
		)
	}
}