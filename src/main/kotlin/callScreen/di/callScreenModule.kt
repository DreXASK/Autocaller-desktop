package callScreen.di

import callScreen.data.repository.contacts.ContactRepositoryLocalCSV
import callScreen.data.repository.contacts.ContactRepositoryLocalJSON
import callScreen.domain.ContactTable
import callScreen.domain.usecase.GetContactListUseCase
import callScreen.presentation.components.contactTable.ContactTableFilterStore
import callScreen.domain.repository.contacts.ContactRepository
import callScreen.domain.usecase.CreateCallTaskDtoList
import callScreen.domain.usecase.GetFilteredContactListUseCase
import core.domain.usecase.callTasks.SendCallTaskDtoListUseCase
import org.koin.dsl.module
import callScreen.presentation.CallScreenViewModel
import org.koin.core.qualifier.named

val callScreenModule = module {
    single {
        CallScreenViewModel()
    }
    single(named(Qualifiers.FileTypes.JSON)) {
        GetContactListUseCase(contactRepository = get(named(Qualifiers.FileTypes.JSON)))
    }
    single(named(Qualifiers.FileTypes.CSV)) {
        GetContactListUseCase(contactRepository = get(named(Qualifiers.FileTypes.CSV)))
    }
    single {
        CreateCallTaskDtoList()
    }
    single {
        GetFilteredContactListUseCase()
    }
    single<ContactRepository>(named(Qualifiers.FileTypes.JSON))  {
        ContactRepositoryLocalJSON()
    }
    single<ContactRepository>(named(Qualifiers.FileTypes.CSV))  {
        ContactRepositoryLocalCSV()
    }
    single(named(Qualifiers.Remoteness.REMOTE)) {
        SendCallTaskDtoListUseCase(callTaskRepository = get(named(Qualifiers.Remoteness.REMOTE)))
    }
    factory {
        ContactTableFilterStore()
    }
    factory {
        ContactTable()
    }
}