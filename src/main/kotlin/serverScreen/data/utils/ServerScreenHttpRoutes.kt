package serverScreen.data.utils

import core.data.utils.CoreHttpRoutes.BASE_URL

object ServerScreenHttpRoutes {
    const val GET_COMPLETED_CALL_TASKS = "$BASE_URL/get_completed_tasks_from_server"
    const val GET_CALL_PROCESS_SETTINGS = "$BASE_URL/get_call_process_settings_from_server"
    const val SEND_CALL_PROCESS_SETTINGS = "$BASE_URL/send_call_process_settings_to_server"
}