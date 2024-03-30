package serverScreen.di

import callScreen.presentation.components.contactTable.ContactTableStore
import serverScreen.domain.repository.ClientTokenRepository
import serverScreen.domain.usecase.CheckConnectionStatusUseCase
import serverScreen.domain.usecase.GetClientTokenUseCase
import org.koin.dsl.module
import serverScreen.data.remote.ClientTokenRepositoryRemote
import serverScreen.domain.TasksTable
import serverScreen.domain.usecase.GetFilteredTasksListUseCase
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.ServerControlPanelSettings
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable.TasksTableFilterStore
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable.TasksTableStore

val ServerScreenModule = module {
	single {
		ServerScreenViewModel()
	}
	single {
		ServerControlPanelSettings()
	}
	single<ClientTokenRepository> {
		ClientTokenRepositoryRemote()
	}
	single {
		GetClientTokenUseCase()
	}
	single {
		CheckConnectionStatusUseCase()
	}
	single {
		TasksTable()
	}
	single {
		GetFilteredTasksListUseCase()
	}
	factory {
		TasksTableStore()
	}
	factory {
		TasksTableFilterStore()
	}
}