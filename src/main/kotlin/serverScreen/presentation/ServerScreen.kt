package serverScreen.presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import core.domain.AdminAuthorization
import core.domain.AuthStatus
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.components.AuthorizationAttemptWindow
import serverScreen.presentation.components.AuthorizationWindow
import serverScreen.presentation.components.ServerControlPanelWindow


@Preview
@Composable
fun ServerScreen() {
	val auth by inject<AdminAuthorization>(AdminAuthorization::class.java)

	when(auth.authStatus.value) {
		AuthStatus.Authorized -> ServerControlPanelWindow()
		AuthStatus.Authorization -> AuthorizationAttemptWindow()
		AuthStatus.Unauthorized -> AuthorizationWindow()
	}
}