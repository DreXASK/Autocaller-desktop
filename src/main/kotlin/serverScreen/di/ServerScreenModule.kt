package serverScreen.di

import serverScreen.domain.repository.ClientTokenRepository
import serverScreen.domain.usecase.CheckConnectionStatusUseCase
import serverScreen.domain.usecase.GetClientTokenUseCase
import org.koin.dsl.module
import serverScreen.data.remote.ClientTokenRepositoryRemote
import serverScreen.presentation.ServerScreenViewModel
import serverScreen.presentation.components.serverControlPanel.ServerControlPanelSettings

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
}