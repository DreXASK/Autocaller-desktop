package serverScreen.data.remote

object ServerScreenHttpRoutes {
	private const val BASE_URL = "http://localhost:8080"
	const val getAdminToken = "$BASE_URL/admin_authorization"
}