package core.domain

import androidx.compose.runtime.mutableStateOf

class AdminAuthorization {
	val authStatus = mutableStateOf(AuthStatus.Authorized)
}
enum class AuthStatus(val text: String) {
	Authorized("Авторизован"),
	Authorization("Авторизирование"),
	Unauthorized("Не авторизован")
}