package com.jslps.empvisist.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    companion object {
        val KEY_USER_TOKEN = stringPreferencesKey("user_token")
        val KEY_USER_NAME = stringPreferencesKey("user_name")
        val KEY_USER_PASSWORD = stringPreferencesKey("user_password")
        val KEY_PERMISSION_STATUS = booleanPreferencesKey("permission_status")
        val KEY_IS_LOGGED_IN = stringPreferencesKey("is_logged_in")
    }

    // Save value
    suspend fun saveValue(key: Preferences.Key<String>, value: String) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    // Read value
    fun getValue(key: Preferences.Key<String>): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[key]
        }
    }

    suspend fun saveBoolean(key: Preferences.Key<Boolean>, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }
    fun getPermissionStatus(key: Preferences.Key<Boolean>): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[key] ?: false
        }
    }

    // Clear all data
    suspend fun clearAll() {
        dataStore.edit { it.clear() }
    }
}
