package core.data.serverConnectionSettings

import core.data.utils.SETTINGS_FILE_NAME
import core.domain.ApiError
import core.domain.Result
import core.domain.repository.ServerConnectionSettingsRepository
import core.domain.utils.EncryptionDecryptionUtil
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileNotFoundException

class ServerConnectionSettingsRepositoryLocal: ServerConnectionSettingsRepository {

    override suspend fun readSettings(): Result<ServerConnectionSettingsDto, ApiError.ServerConnectionSettingsError>  {
        val base64CipherText: String

        try {
            base64CipherText = File(SETTINGS_FILE_NAME).readText()
        } catch (e: FileNotFoundException) {
            return Result.Error(ApiError.ServerConnectionSettingsError.FILE_DOES_NOT_EXIST)
        }

        val decryptedJson = EncryptionDecryptionUtil.decrypt("secret", base64CipherText)
        val result = Json.decodeFromString<ServerConnectionSettingsDto>(decryptedJson)

        return Result.Success(result)
    }

    override suspend fun writeSettings(serverConnectionSettings: ServerConnectionSettingsDto): Result<Unit, ApiError> {
        val dataJson = Json.encodeToString(serverConnectionSettings)

        try {
            File(SETTINGS_FILE_NAME).writeText(EncryptionDecryptionUtil.encrypt("secret", dataJson))
        } catch (e: Exception) {
            return Result.Error(ApiError.UnknownError)
        }

        return Result.Success(Unit)
    }

    override suspend fun deleteSettings(): Result<Unit, ApiError> {
        try {
            File(SETTINGS_FILE_NAME).delete()
        } catch (e: Exception) {
            return Result.Error(ApiError.UnknownError)
        }
        return Result.Success(Unit)
    }

}
