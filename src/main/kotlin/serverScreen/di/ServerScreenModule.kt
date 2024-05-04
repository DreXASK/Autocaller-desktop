package serverScreen.di

import org.koin.dsl.module
import serverScreen.domain.CompletedTasksTable
import serverScreen.domain.TasksTable
import serverScreen.domain.usecase.GetFilteredCompletedTaskListUseCase
import serverScreen.domain.usecase.GetFilteredTaskListUseCase
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.ServerControlPanel
import serverScreen.presentation.components.serverControlPanel.tabs.completedTasksWindow.completedTasksTable.CompletedTasksTableFilterStore
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
	single {
		CompletedTasksTable()
	}
	single {
		GetFilteredCompletedTaskListUseCase()
	}
	single {
		CompletedTasksTableFilterStore()
	}
}