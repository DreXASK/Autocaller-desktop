package serverScreen.presentation

import core.domain.AdminAuthorization
import core.domain.AuthStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import serverScreen.domain.usecase.GetAdminTokenUseCase

class ServerScreenViewModel {
	val auth by inject<AdminAuthorization>(AdminAuthorization::class.java)
	private val getAdminTokenUseCase by inject<GetAdminTokenUseCase>(GetAdminTokenUseCase::class.java)

	fun logIn() {
		CoroutineScope(Dispatchers.Default).launch {
			auth.authStatus.value = AuthStatus.Authorization
			println(getAdminTokenUseCase.execute())
		}
	}
}