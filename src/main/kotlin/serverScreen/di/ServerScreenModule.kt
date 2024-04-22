package serverScreen.di

import core.domain.repository.TokenRepository
import core.domain.usecase.GetTokenStatusUseCase
import core.domain.usecase.GetTokenUseCase
import org.koin.dsl.module
import core.data.TokenStatusRepositoryRemote
import core.data.TokenRepositoryRemote
import serverScreen.domain.TasksTable
import core.domain.repository.TokenStatusRepository
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