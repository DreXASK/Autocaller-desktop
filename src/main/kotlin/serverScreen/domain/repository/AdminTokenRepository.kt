package serverScreen.domain.repository

import io.ktor.client.statement.*

interface AdminTokenRepository {
	suspend fun getAdminToken(): HttpResponse
}