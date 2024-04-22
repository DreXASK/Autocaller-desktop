package serverScreen.presentation.components.serverControlPanel.tabs.tasksWindow.tasksTable

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class TasksTableFilterStore(
    val surnameFilterText: MutableState<String> = mutableStateOf(""),
    val nameFilterText: MutableState<String> = mutableStateOf(""),
    val patronymicFilterText: MutableState<String> = mutableStateOf(""),
    val phoneNumberFilterText: MutableState<String> = mutableStateOf(""),
    val messageTemplateFilterText: MutableState<String> = mutableStateOf("")
)
