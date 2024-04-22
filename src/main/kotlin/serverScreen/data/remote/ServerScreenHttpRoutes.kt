package serverScreen.data.remote

object ServerScreenHttpRoutes {
    private const val BASE_URL = "http://localhost:8080"
    const val GET_TOKEN = "$BASE_URL/register_client"
    const val GET_CONNECTION_STATUS = "$BASE_URL/check_token_status"
}