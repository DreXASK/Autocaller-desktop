package serverScreen.di

import serverScreen.domain.repository.TokenRepository
import serverScreen.domain.usecase.GetConnectionStatusUseCase
import serverScreen.domain.usecase.GetTokenUseCase
import org.koin.dsl.module
import serverScreen.data.remote.ConnectionRepositoryRemote
import serverScreen.data.remote.TokenRepositoryRemote
import serverScreen.domain.TasksTable
import serverScreen.domain.repository.ConnectionRepository
import serverScreen.domain.usecase.GetFilteredTaskListUseCase
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.ServerControlPanel
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable.TasksTableFilterStore

val ServerScreenModule = module {
	single {
		ServerScreenViewModel()
	}
	single {
		ServerControlPanel()
	}
	single<TokenRepository> {
		TokenRepositoryRemote(httpClient = get())
	}
	single<ConnectionRepository> {
		ConnectionRepositoryRemote(httpClient = get())
	}
	single {
		GetTokenUseCase(tokenRepository = get())
	}
	single {
		GetConnectionStatusUseCase(connectionRepository = get())
	}
	single {
		TasksTable()
	}
	single {
		GetFilteredTaskListUseCase()
	}
	single {
		TasksTableFilterStore()
	}
}