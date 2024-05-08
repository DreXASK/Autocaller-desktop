package core.data.utils

object CoreHttpRoutes {
    const val BASE_URL = "http://localhost:8080"

    const val GET_TOKEN_STATUS = "$BASE_URL/check_token_status"

    const val GET_CALL_TASKS = "${BASE_URL}/get_tasks_from_server"
    const val SEND_CALL_TASKS = "${BASE_URL}/send_tasks_to_server"
    const val REMOVE_CALL_TASK = "${BASE_URL}/remove_task_from_server"

    const val GET_MESSAGE_TEMPLATES = "${BASE_URL}/get_message_templates_from_server"
    const val REMOVE_MESSAGE_TEMPLATE = "${BASE_URL}/remove_message_template_from_server"
    const val SEND_MESSAGE_TEMPLATE = "${BASE_URL}/send_message_template_to_server"
}