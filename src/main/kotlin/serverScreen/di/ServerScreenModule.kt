package serverScreen.di

import org.koin.dsl.module
import serverScreen.data.remote.AdminTokenRepositoryRemote
import serverScreen.domain.repository.AdminTokenRepository
import serverScreen.domain.usecase.GetAdminTokenUseCase
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.ServerControlPanelSettings

val ServerScreenModule = module {
	single {
		ServerScreenViewModel()
	}
	single<AdminTokenRepository> {
		AdminTokenRepositoryRemote()
	}
	single {
		GetAdminTokenUseCase()
	}
	single {
		ServerControlPanelSettings()
	}
}