package connectionAdjusterScreen.data.remote

object ConnectionAdjusterHttpRoutes {
    private const val BASE_URL = "http://localhost:8080"
    const val getToken = "$BASE_URL/registerClient"
    const val checkTokenStatus = "$BASE_URL/check_token_status"
}