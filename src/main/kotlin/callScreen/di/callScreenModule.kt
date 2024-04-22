package callScreen.di

import callScreen.data.repository.local.ContactRepositoryLocal
import callScreen.domain.ContactTable
import callScreen.domain.usecase.GetContactListFromFileUseCase
import callScreen.presentation.components.contactTable.ContactTableFilterStore
import callScreen.domain.repository.ContactRepository
import callScreen.domain.usecase.GetFilteredContactListUseCase
import org.koin.dsl.module
import callScreen.presentation.CallScreenViewModel

val callScreenModule = module {
    single {
        CallScreenViewModel()
    }
    single {
        GetContactListFromFileUseCase(contactRepository = get())
    }
    single {
        GetFilteredContactListUseCase()
    }
    single<ContactRepository>  {
        ContactRepositoryLocal()
    }
    factory {
        ContactTableFilterStore()
    }
    factory {
        ContactTable()
    }
}