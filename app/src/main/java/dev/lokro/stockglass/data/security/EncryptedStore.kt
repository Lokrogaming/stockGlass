package dev.lokro.stockglass.data.security

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object EncryptedStore {
    private const val PREFS_NAME = "stockglass_secure_prefs"
    private const val KEY_API_KEY = "twelvedata_api_key"
    private const val KEY_API_KEY_VALIDATED = "twelvedata_api_key_validated"

    private var prefs: androidx.security.crypto.EncryptedSharedPreferences? = null

    fun init(context: Context) {
        if (prefs != null) return
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        prefs = EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
    }

    fun storeApiKey(apiKey: String) {
        prefs?.edit()?.putString(KEY_API_KEY, apiKey)?.apply()
    }

    fun getApiKey(): String? = prefs?.getString(KEY_API_KEY, null)

    fun deleteApiKey() {
        prefs?.edit()?.remove(KEY_API_KEY)?.apply()
        prefs?.edit()?.remove(KEY_API_KEY_VALIDATED)?.apply()
    }

    fun markKeyValidated() {
        prefs?.edit()?.putBoolean(KEY_API_KEY_VALIDATED, true)?.apply()
    }

    fun isKeyValidated(): Boolean = prefs?.getBoolean(KEY_API_KEY_VALIDATED, false) ?: false
}
