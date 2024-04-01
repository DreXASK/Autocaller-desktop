package serverScreen.di

import serverScreen.domain.repository.ClientTokenRepository
import serverScreen.domain.usecase.CheckConnectionStatusUseCase
import serverScreen.domain.usecase.GetClientTokenUseCase
import org.koin.dsl.module
import serverScreen.data.remote.ClientTokenRepositoryRemote
import serverScreen.domain.ClientsTable
import serverScreen.domain.ConnectionRequestsTable
import serverScreen.domain.TasksTable
import serverScreen.domain.usecase.AddTaskToTableUseCase
import serverScreen.domain.usecase.GetFilteredTasksListUseCase
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
		TasksTableFilterStore()
	}
	single {
		ConnectionRequestsTable()
	}
	single {
		ClientsTable()
	}
	single {
		AddTaskToTableUseCase()
	}
}