package serverScreen.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import core.domain.AdminAuthorization
import core.domain.AuthStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import serverScreen.domain.usecase.GetAdminTokenUseCase
import serverScreen.presentation.components.serverControlPanel.ServerControlPanelSettings
import serverScreen.presentation.components.serverControlPanel.ServerControlPanelWindows

class ServerScreenViewModel {
	val auth by inject<AdminAuthorization>(AdminAuthorization::class.java)
	private val getAdminTokenUseCase by inject<GetAdminTokenUseCase>(GetAdminTokenUseCase::class.java)
	val serverControlPanelSettings by inject<ServerControlPanelSettings>(ServerControlPanelSettings::class.java)

	fun logIn() {
		CoroutineScope(Dispatchers.Default).launch {
			auth.authStatus.value = AuthStatus.Authorization
			println(getAdminTokenUseCase.execute())
		}
	}
}