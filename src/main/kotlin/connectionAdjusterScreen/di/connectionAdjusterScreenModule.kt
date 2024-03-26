package connectionAdjusterScreen.di

import connectionAdjusterScreen.domain.repository.ClientTokenRepository
import connectionAdjusterScreen.data.remote.ClientTokenRepositoryRemote
import connectionAdjusterScreen.domain.usecase.CheckConnectionStatusUseCase
import connectionAdjusterScreen.domain.usecase.GetClientTokenUseCase
import connectionAdjusterScreen.presentation.ConnectionAdjusterScreenViewModel
import org.koin.dsl.module

val connectionAdjusterScreenModule = module {
    single {
        ConnectionAdjusterScreenViewModel()
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