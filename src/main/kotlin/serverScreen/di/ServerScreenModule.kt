package serverScreen.di

import org.koin.dsl.module
import serverScreen.domain.TasksTable
import serverScreen.domain.usecase.GetFilteredTaskListUseCase
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.ServerControlPanel
import serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable.TasksTableFilterStore

val serverScreenModule = module {
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