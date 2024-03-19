package connectionAdjusterScreen.data.remote

object HttpRoutes {
    private const val BASE_URL = "http://localhost:8080"
    const val getToken = "$BASE_URL/registerClient"
    const val checkTokenStatus = "$BASE_URL/check_token_status"
}