package serverScreen.presentation.components.serverControlPanel.tabs.completedTasksWindow.completedTasksTable

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class CompletedTasksTableFilterStore(
    val surnameFilterText: MutableState<String> = mutableStateOf(""),
    val nameFilterText: MutableState<String> = mutableStateOf(""),
    val patronymicFilterText: MutableState<String> = mutableStateOf(""),
    val phoneNumberFilterText: MutableState<String> = mutableStateOf(""),
    val messageTemplateFilterText: MutableState<String> = mutableStateOf(""),
    val callAttempts: MutableState<String> = mutableStateOf(""),
    val isSmsUsed: MutableState<Boolean> = mutableStateOf(false)
)
