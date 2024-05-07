package callScreen.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.mutableStateOf
import callScreen.data.repository.contacts.ContactsParameterGetLocal
import callScreen.di.Qualifiers
import callScreen.domain.*
import callScreen.domain.usecase.CreateCallTaskList
import callScreen.domain.usecase.GetContactListUseCase
import core.domain.usecase.SendCallTasksListUseCase
import core.data.repository.callTasks.CallTasksParameterSendRemote
import core.domain.models.MessageTemplateData
import core.domain.utils.Result
import core.domain.utils.ServerConnection
import core.presentation.CustomFileDialog
import org.koin.java.KoinJavaComponent.inject
import core.presentation.components.buttonTab.ButtonTabData
import core.presentation.utils.useNonBreakingSpace
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.get


class CallScreenViewModel {
    val contactTable by inject<ContactTable>(ContactTable::class.java)

    var isContactAdderDialogOpen = mutableStateOf(false)
    var isSenderContactsToServerDialogOpen = mutableStateOf(false)
    var isLoadingFileTypePickerDialogOpen = mutableStateOf(false)

    val buttonsDataList = listOf(
        ButtonTabData(
            onClick = { isContactAdderDialogOpen.value = true },
            icon = Icons.Rounded.Add,
            text = "Добавить контакт".useNonBreakingSpace(),
        ),
        ButtonTabData(
            onClick = { isLoadingFileTypePickerDialogOpen.value = true },
            icon = Icons.Rounded.List,
            text = "Загрузить список контактов".useNonBreakingSpace(),
        ),
        ButtonTabData(
            onClick = { isSenderContactsToServerDialogOpen.value = true },
            icon = Icons.Rounded.Send,
            text = "Обзвонить список".useNonBreakingSpace(),
        )
    )

    fun loadFile(qualifier: Qualifiers.FileTypes) {
        CoroutineScope(Dispatchers.IO).launch {
            val filePath = CustomFileDialog.getFilePath()
            filePath?.let {
                val useCase =
                    get<GetContactListUseCase>(GetContactListUseCase::class.java, qualifier = named(qualifier))
                when (val result = useCase.execute(ContactsParameterGetLocal(it))) {
                    is Result.Success -> {
                        contactTable.addContactListToTable(result.data)
                        contactTable.updateContactListFiltered()
                    }

                    is Result.Error -> TODO()
                }
            }
        }
    }

    fun sendContactsToServer(
        messageTemplateData: MessageTemplateData
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val createCallTaskListUseCase =
                get<CreateCallTaskList>(CreateCallTaskList::class.java)
            val sendUseCase =
                get<SendCallTasksListUseCase>(SendCallTasksListUseCase::class.java)
            val serverConnection = get<ServerConnection>(ServerConnection::class.java)

            serverConnection.serverConnectionSettings?.let {

                val creationListResult =
                    createCallTaskListUseCase.execute(contactTable.contactListFiltered, messageTemplateData)

                if (creationListResult is Result.Error) {
                    println("Creating list result error - ${creationListResult.error}")
                    this.cancel()
                }

                val callTaskList = creationListResult as Result.Success

                when (val result = sendUseCase.execute(
                    CallTasksParameterSendRemote(
                        it.token,
                        callTaskList.data,
                    )
                )
                ) {
                    is Result.Success -> {
                        println("Contact list has been successfully transmitted")
                    }

                    is Result.Error -> {
                        println("Error = ${result.error}")
                        TODO()
                    }
                }
            }
        }
    }
}
