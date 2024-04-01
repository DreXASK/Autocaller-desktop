package serverScreen.presentation


import core.domain.ServerConnection
import org.koin.java.KoinJavaComponent.inject
import serverScreen.domain.ClientsTable
import serverScreen.domain.ConnectionRequestsTable
import serverScreen.domain.TasksTable
import serverScreen.domain.models.TasksTableItemData
import serverScreen.presentation.components.serverControlPanel.ServerControlPanel

class ServerScreenViewModel {
    val serverControlPanel by inject<ServerControlPanel>(ServerControlPanel::class.java)
    val serverConnection by inject<ServerConnection>(ServerConnection::class.java)
    val tasksTable by inject<TasksTable>(TasksTable::class.java)
    val connectionRequestsTable by inject<ConnectionRequestsTable>(ConnectionRequestsTable::class.java)
    val clientsTable by inject<ClientsTable>(ClientsTable::class.java)


}

