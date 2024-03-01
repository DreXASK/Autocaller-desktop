package di.modules

import org.koin.dsl.module
import callScreen.presentation.CallScreenViewModel
import viewModels.ConnectionScreenViewModel

val viewModelsModule = module {
    single {
        CallScreenViewModel()
    }
    single {
        ConnectionScreenViewModel()
    }
}