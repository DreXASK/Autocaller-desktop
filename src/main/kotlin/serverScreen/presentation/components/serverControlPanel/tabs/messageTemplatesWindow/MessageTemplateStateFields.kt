package serverScreen.presentation.components.serverControlPanel.tabs.messageTemplatesWindow

import androidx.compose.runtime.mutableStateOf
import serverScreen.domain.models.MessageTemplatePlaceholders

class MessageTemplateStateFields {

    init {
        println("MessageTemplateStateFields created")
    }

    var templateName = mutableStateOf("")
    var templateText = mutableStateOf("")
    var templatePlaceholders = mutableStateOf(MessageTemplatePlaceholders())

}