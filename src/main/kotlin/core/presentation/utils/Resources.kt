package core.presentation.utils
import App
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object Res {
    val str: StringsResourcesByLanguages.StringInterface
        get() {
            return if (App.language == App.Languages.Russian)
                StringsResourcesByLanguages.Russian
            else
                StringsResourcesByLanguages.English
        }


    object StringsResourcesByLanguages {

        // %1$s %2$s etc. for string parameters

        interface StringInterface {
            val call_screen_label: MutableState<String>
            val connection_to_server_Label: MutableState<String>
            val server_label: MutableState<String>
        }

        abstract class Common: StringInterface {
            // override val call_manager_label = "Common text value for all languages"
        }

        object English: StringInterface, Common() {
            override val call_screen_label = mutableStateOf("Calls")
            override val connection_to_server_Label: MutableState<String> = mutableStateOf("Connection")
            override val server_label = mutableStateOf("Server")
        }

        object Russian: StringInterface, Common() {
            override val call_screen_label = mutableStateOf("Обзвон")
            override val connection_to_server_Label: MutableState<String> = mutableStateOf("Соединение")
            override val server_label = mutableStateOf("Сервер")
        }

    }
}



fun getStringResourceWithParameter(stringResource: MutableState<String>, vararg parameters: String): MutableState<String> {
    var currentParameterCount = 1
    while(true) {
        if(!stringResource.value.contains("%${currentParameterCount}\$s")) {
            break
        }
        if (parameters.count() < currentParameterCount)
            throw Exception("Insufficient parameters given, given parameters count = ${parameters.count()}, stringResource = $stringResource")

        stringResource.value = stringResource.value.replace("%${currentParameterCount}\$s", parameters[currentParameterCount - 1])
        currentParameterCount++
    }
    return stringResource
}