package core.data.utils

object CoreHttpRoutes {
    const val BASE_URL = "http://localhost:8080"
    const val GET_TOKEN_STATUS = "$BASE_URL/check_token_status"
    const val SEND_TASKS = "${BASE_URL}/send_tasks"
}