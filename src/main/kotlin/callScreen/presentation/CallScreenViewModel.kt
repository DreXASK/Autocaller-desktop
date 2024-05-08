package callScreen.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import callScreen.data.repository.contacts.ContactsParameterGetLocal
import callScreen.di.Qualifiers
import callScreen.domain.*
import callScreen.domain.usecase.CreateCallTaskDtoList
import callScreen.domain.usecase.GetContactListUseCase
import core.domain.usecase.callTasks.SendCallTaskDtoListUseCase
import core.data.repository.callTasks.CallTaskParameterSendRemote
import core.data.repository.messageTemplates.MessageTemplateParameterGetRemote
import core.domain.models.MessageTemplateData
import core.domain.utils.ApiError
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
import serverScreen.domain.MessageTemplateService


class CallScreenViewModel {
    val contactTable by inject<ContactTable>(ContactTable::class.java)
    private val serverConnection by inject<ServerConnection>(ServerConnection::class.java)
    val messageTemplateService by inject<MessageTemplateService>(MessageTemplateService::class.java)


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
            val createCallTaskDtoListUseCase =
                get<CreateCallTaskDtoList>(CreateCallTaskDtoList::class.java)
            val sendUseCase =
                get<SendCallTaskDtoListUseCase>(SendCallTaskDtoListUseCase::class.java)

            if(contactTable.contactListFiltered.size == 0) {
                println("SendContactsToServer error - ContactListFiltered is empty")
                return@launch
            }

            serverConnection.serverConnectionSettings?.let {

                val creationListResult =
                    createCallTaskDtoListUseCase.execute(contactTable.contactListFiltered, messageTemplateData)

                if (creationListResult is Result.Error) {
                    println("Creating list result error - ${creationListResult.error}")
                    this.cancel()
                }

                val callTaskList = creationListResult as Result.Success

                when (val result = sendUseCase.execute(
                    CallTaskParameterSendRemote(
                        it.token,
                        callTaskList.data,
                    )
                )
                ) {
                    is Result.Success -> {
                        println("Contact list has been successfully transmitted to server")
                    }

                    is Result.Error -> {
                        println("SendCallTasksListUseCase error - ${result.error}")
                        //TODO()
                    }
                }
            }
        }
    }

    fun getMessageTemplatesFromServer() {
        CoroutineScope(Dispatchers.IO).launch {
            val messageTemplateDtoParameter =
                serverConnection.serverConnectionSettings?.let { MessageTemplateParameterGetRemote(it.token) }

            if (messageTemplateDtoParameter == null) {
                println("getMessageTemplatesFromServer error - serverConnectionSettings is null")
                return@launch
            }

            when (val result = messageTemplateService.getMessageTemplatesFromServer(messageTemplateDtoParameter)) {
                is Result.Success -> {
                    messageTemplateService.addMessageTemplates(result.data)
                    //TODO()
                }
                is Result.Error -> {
                    when (result.error) {
                        is ApiError.MessageTemplatesError.Remote.UnknownError -> {
                            println("loadMessageTemplatesFromServer error - ${result.error}")
                            //TODO()
                        }
                    }
                }
            }
        }
    }
}
