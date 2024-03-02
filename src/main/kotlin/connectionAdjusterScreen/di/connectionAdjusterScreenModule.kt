package connectionAdjusterScreen.di

import connectionAdjusterScreen.presentation.ConnectionAdjusterScreenViewModel
import core.domain.ServerConnection
import org.koin.dsl.module

val connectionAdjusterScreenModule = module {
    single {
        ConnectionAdjusterScreenViewModel()
    }
}