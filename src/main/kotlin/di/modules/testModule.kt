package di.modules

import org.koin.dsl.module
import viewModels.CallScreenViewModel
import viewModels.ConnectionScreenViewModel

val testModule = module {
    single {
        CallScreenViewModel()
    }
    single {
        ConnectionScreenViewModel()
    }
}