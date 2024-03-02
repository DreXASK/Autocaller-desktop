package callScreen.di

import callScreen.domain.usecase.AddContactsToTableUseCase
import callScreen.domain.CallTableFilterStore
import callScreen.domain.CallTableStore
import callScreen.domain.usecase.GetFilteredContactListUseCase
import org.koin.dsl.module
import callScreen.presentation.CallScreenViewModel

val callScreenModule = module {
    single {
        CallScreenViewModel()
    }
    single {
        AddContactsToTableUseCase()
    }
    single {
        GetFilteredContactListUseCase()
    }
    factory {
        CallTableStore()
    }
    factory {
        CallTableFilterStore()
    }
}