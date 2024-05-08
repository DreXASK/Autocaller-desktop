package serverScreen.di

import org.koin.dsl.module
import serverScreen.data.repository.completedCallTasks.CompletedTaskRepositoryRemote
import serverScreen.domain.CompletedTasksTable
import serverScreen.presentation.components.serverControlPanel.tabs.messageTemplatesWindow.MessageTemplateStateFields
import serverScreen.domain.CallTasksTable
import serverScreen.domain.repository.completedTasks.CompletedTaskRepository
import serverScreen.domain.usecase.GetCompletedTaskDataListUseCase
import serverScreen.domain.usecase.GetFilteredCompletedTaskListUseCase
import serverScreen.domain.usecase.GetFilteredTaskListUseCase
import serverScreen.domain.usecase.RemoveCallTaskUseCase
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.ServerControlPanel
import serverScreen.presentation.components.serverControlPanel.tabs.completedTasksWindow.completedTasksTable.CompletedTasksTableFilterStore
import serverScreen.presentation.components.serverControlPanel.tabs.callTasksWindow.callTasksTable.CallTasksTableFilterStore

val serverScreenModule = module {
	single {
		ServerScreenViewModel()
	}
	single {
		ServerControlPanel()
	}
	single {
		CallTasksTable(getFilteredTaskListUseCase = get(), filterStore = get())
	}
	single {
		GetFilteredTaskListUseCase()
	}
	single {
		CallTasksTableFilterStore()
	}
	single {
		RemoveCallTaskUseCase(callTaskRepository = get())
	}
	single {
		CompletedTasksTable(getFilteredTaskListUseCase = get(), filterStore = get())
	}
	single {
		GetFilteredCompletedTaskListUseCase()
	}
	single {
		CompletedTasksTableFilterStore()
	}
	single<CompletedTaskRepository> {
		CompletedTaskRepositoryRemote(httpClient = get())
	}
	single {
		GetCompletedTaskDataListUseCase(completedTaskRepository = get())
	}
	factory {
		MessageTemplateStateFields()
	}
}