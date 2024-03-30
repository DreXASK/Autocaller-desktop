package serverScreen.presentation


import core.domain.ServerConnection
import org.koin.java.KoinJavaComponent.inject
import serverScreen.domain.TasksTable
import serverScreen.domain.models.TasksTableItemData
import serverScreen.presentation.components.serverControlPanel.ServerControlPanelSettings

class ServerScreenViewModel {
    val serverControlPanelSettings by inject<ServerControlPanelSettings>(ServerControlPanelSettings::class.java)
    val serverConnection by inject<ServerConnection>(ServerConnection::class.java)
    val tasksTable by inject<TasksTable>(TasksTable::class.java)

    init {
        tasksTable.tasksTableStore.tasksList.addAll(
            listOf(
                TasksTableItemData(
                    "Крючков",
                    "Илья",
                    "Николаевич",
                    "89020285716",
                    "Мессаге темплате"
                ),
                TasksTableItemData(
                    "Крючков",
                    "Илья",
                    "Николаевич",
                    "89020285716",
                    "Мессаге темплате 2"
                ),
                TasksTableItemData(
                    "Крючков",
                    "Илья",
                    "Николаевич",
                    "89020285716",
                    "Мессаге темплате 3"
                ),
            )
        )
        tasksTable.updateTasksListFiltered()
    }
}

