package serverScreen.presentation.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import core.domain.AdminAuthorization
import core.domain.AuthStatus
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.inject
import serverScreen.presentation.ServerScreenViewModel

@Preview
@Composable
fun AuthorizationWindow() {
	val viewModel by inject<ServerScreenViewModel>(ServerScreenViewModel::class.java)

	var login by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }
	val textFieldWidth = 300.dp

	Column(
		Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			"Панель управления сервером",
			Modifier.padding(10.dp)
		)

		OutlinedTextField(
			value = login,
			onValueChange = {
				login = it
			},
			label = { Text("Логин") },
			singleLine = true,
			modifier = Modifier.width(textFieldWidth)
		)

		OutlinedTextField(
			value = password,
			onValueChange = {
				password = it
			},
			label = { Text("Пароль") },
			visualTransformation = PasswordVisualTransformation(),
			singleLine = true,
			modifier = Modifier.width(textFieldWidth)
		)

		OutlinedButton(
			onClick = {
				viewModel.logIn()
			},
			modifier = Modifier.padding(10.dp)
		) {
			Text("ВОЙТИ")
		}
	}
}