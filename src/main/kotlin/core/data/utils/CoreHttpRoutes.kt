package core.data.utils

object CoreHttpRoutes {
    const val BASE_URL = "http://localhost:8080"
    const val GET_TOKEN_STATUS = "$BASE_URL/check_token_status"
    const val GET_TASKS = "${BASE_URL}/get_tasks_from_server"
    const val SEND_TASKS = "${BASE_URL}/send_tasks_to_server"
    const val REMOVE_TASKS = "${BASE_URL}/remove_task_from_server"
}