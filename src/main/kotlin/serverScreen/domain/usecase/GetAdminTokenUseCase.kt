package serverScreen.domain.usecase

import io.ktor.client.call.*
import org.koin.java.KoinJavaComponent.inject
import serverScreen.data.remote.dto.AdminTokenRequest
import serverScreen.domain.repository.AdminTokenRepository

class GetAdminTokenUseCase {
	private val adminTokenRepository by inject<AdminTokenRepository>(AdminTokenRepository::class.java)
	suspend fun execute(): String {
		return adminTokenRepository.getAdminToken().body<AdminTokenRequest>().adminToken
	}
}