package serverScreen.di

import org.koin.dsl.module
import serverScreen.domain.CompletedTasksTable
import serverScreen.presentation.components.serverControlPanel.tabs.messageTemplatesWindow.MessageTemplateStateFields
import serverScreen.domain.CallTasksTable
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
		CompletedTasksTable(getFilteredTaskListUseCase = get(), filterStore = get())
	}
	single {
		GetFilteredCompletedTaskListUseCase()
	}
	single {
		CompletedTasksTableFilterStore()
	}
	single {
		RemoveCallTaskUseCase(callTaskRepository = get())
	}
	factory {
		MessageTemplateStateFields()
	}
}