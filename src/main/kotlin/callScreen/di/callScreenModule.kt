package callScreen.di

import callScreen.data.repository.local.ContactRepositoryLocal
import callScreen.domain.ContactTable
import callScreen.domain.usecase.AddContactsToTableFromFileUseCase
import callScreen.domain.ContactTableFilterStore
import callScreen.domain.ContactTableStore
import callScreen.domain.repository.ContactRepository
import callScreen.domain.usecase.AddContactToTableUseCase
import callScreen.domain.usecase.GetFilteredContactListUseCase
import org.koin.dsl.module
import callScreen.presentation.CallScreenViewModel

val callScreenModule = module {
    single {
        CallScreenViewModel()
    }
    single {
        AddContactsToTableFromFileUseCase()
    }
    single{
        AddContactToTableUseCase()
    }
    single {
        GetFilteredContactListUseCase()
    }
    single<ContactRepository>  {
        ContactRepositoryLocal()
    }
    factory {
        ContactTableStore()
    }
    factory {
        ContactTableFilterStore()
    }
    factory {
        ContactTable()
    }
}