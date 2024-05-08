package serverScreen.presentation.components.serverControlPanel.tabs.callTasksWindow.callTasksTable

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class CallTasksTableFilterStore(
    val surnameFilterText: MutableState<String> = mutableStateOf(""),
    val nameFilterText: MutableState<String> = mutableStateOf(""),
    val patronymicFilterText: MutableState<String> = mutableStateOf(""),
    val phoneNumberFilterText: MutableState<String> = mutableStateOf(""),
    val messageTemplateFilterText: MutableState<String> = mutableStateOf(""),
    val callAttemptsFilterText: MutableState<String> = mutableStateOf("")
)
