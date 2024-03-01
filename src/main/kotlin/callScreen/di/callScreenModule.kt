package callScreen.di

import callScreen.domain.CallTableAddContactsUseCase
import callScreen.domain.CallTableFilterStore
import callScreen.domain.CallTableStore
import callScreen.domain.CallTableUpdateFilterUseCase
import org.koin.dsl.module
import callScreen.presentation.CallScreenViewModel

val callScreenModule = module {
    single {
        CallScreenViewModel()
    }
    single {
        CallTableAddContactsUseCase()
    }
    single {
        CallTableUpdateFilterUseCase()
    }
    factory {
        CallTableStore()
    }
    factory {
        CallTableFilterStore()
    }
}