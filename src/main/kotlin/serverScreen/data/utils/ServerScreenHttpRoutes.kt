package serverScreen.data.utils

import core.data.utils.CoreHttpRoutes.baseUrl

object ServerScreenHttpRoutes {
    val getCompletedCallTasks = "$baseUrl/get_completed_tasks_from_server"
    val getCallProcessSettings = "$baseUrl/get_call_process_settings_from_server"
    val sendCallProcessSettings = "$baseUrl/send_call_process_settings_to_server"
}