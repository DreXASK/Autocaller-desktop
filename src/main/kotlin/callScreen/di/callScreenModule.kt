package callScreen.di

import callScreen.data.repository.local.ContactRepositoryLocalCSV
import callScreen.data.repository.local.ContactRepositoryLocalJSON
import callScreen.domain.ContactTable
import callScreen.domain.usecase.GetContactListFromFileUseCase
import callScreen.presentation.components.contactTable.ContactTableFilterStore
import callScreen.domain.repository.ContactRepository
import callScreen.domain.usecase.GetFilteredContactListUseCase
import org.koin.dsl.module
import callScreen.presentation.CallScreenViewModel
import org.koin.core.qualifier.named

val callScreenModule = module {
    single {
        CallScreenViewModel()
    }
    single(named("JSON")) {
        GetContactListFromFileUseCase(contactRepository = get(named("JSON")))
    }
    single(named("CSV")) {
        GetContactListFromFileUseCase(contactRepository = get(named("CSV")))
    }
    single {
        GetFilteredContactListUseCase()
    }
    single<ContactRepository>(named("JSON"))  {
        ContactRepositoryLocalJSON()
    }
    single<ContactRepository>(named("CSV"))  {
        ContactRepositoryLocalCSV()
    }
    factory {
        ContactTableFilterStore()
    }
    factory {
        ContactTable()
    }
}