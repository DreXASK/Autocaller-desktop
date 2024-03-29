package serverScreen.data.remote

object ServerScreenHttpRoutes {
    private const val BASE_URL = "http://localhost:8080"
    const val getToken = "$BASE_URL/register_client"
    const val checkTokenStatus = "$BASE_URL/check_token_status"
}