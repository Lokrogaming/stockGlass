package dev.lokro.stockglass.data.remote

import dev.lokro.stockglass.data.remote.dto.ErrorDto
import kotlinx.serialization.json.Json
import okhttp3.ResponseBody
import retrofit2.Response

object ErrorBodyParser {
    fun message(errorBody: ResponseBody?): String? {
        if (errorBody == null) return null
        return try {
            val json = errorBody.string()
            Json.decodeFromString(ErrorDto.serializer(), json).message
        } catch (_: Exception) {
            null
        }
    }
}
