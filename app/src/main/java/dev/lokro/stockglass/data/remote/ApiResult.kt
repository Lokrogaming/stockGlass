package dev.lokro.stockglass.data.remote

import dev.lokro.stockglass.data.remote.dto.ErrorDto
import retrofit2.Response
import kotlinx.serialization.SerializationException

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val exception: Exception) : ApiResult<Nothing>()
    data class InvalidKey(val message: String) : ApiResult<Nothing>()
    data class RateLimited(val retryAfterSeconds: Long?) : ApiResult<Nothing>()
    data class InvalidSymbol(val message: String) : ApiResult<Nothing>()
    data class NetworkFailure(val exception: Exception) : ApiResult<Nothing>()
    data class EmptyData(val message: String) : ApiResult<Nothing>()
}

object ApiResultMapper {
    fun <T> fromResponse(response: Response<T>, onEmpty: () -> Unit = {}): ApiResult<T> {
        return when {
            response.isSuccessful -> {
                val body = response.body()
                if (body == null) {
                    ApiResult.Error(IllegalStateException("Empty response body"))
                } else {
                    ApiResult.Success(body)
                }
            }
            response.code() == 401 -> {
                val msg = errorMessage(response.errorBody()) ?: "Ungültiger API-Schlüssel"
                ApiResult.InvalidKey(msg)
            }
            response.code() == 429 -> {
                val retryAfter = response.header("Retry-After")?.toLongOrNull()
                ApiResult.RateLimited(retryAfter)
            }
            response.code() == 404 -> {
                ApiResult.InvalidSymbol(errorMessage(response.errorBody()) ?: "Symbol nicht gefunden")
            }
            else -> {
                ApiResult.Error(Exception("HTTP ${response.code()}: ${errorMessage(response.errorBody()) ?: response.message()}"))
            }
        }
    }

    private fun errorMessage(errorBody: okhttp3.ResponseBody?): String? {
        if (errorBody == null) return null
        return try {
            val json = errorBody.string()
            dev.lokro.stockglass.data.remote.dto.ErrorDto
            // Parse lazily via kotlinx serialization if available
            kotlinx.serialization.json.Json.decodeFromString(
                dev.lokro.stockglass.data.remote.dto.ErrorDto.serializer(),
                json,
            ).message
        } catch (_: Exception) {
            null
        }
    }
}
