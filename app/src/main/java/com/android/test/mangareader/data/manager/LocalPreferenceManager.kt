package com.android.test.mangareader.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.android.test.mangareader.data.model.UserDTO
import com.android.test.mangareader.utils.AppConstants.K_EMAIL
import com.android.test.mangareader.utils.AppConstants.K_ID
import com.android.test.mangareader.utils.AppConstants.K_TOKEN
import com.android.test.mangareader.utils.AppConstants.K_USERNAME
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class LocalPreferenceManager {
    private val prefId = intPreferencesKey(K_ID)
    private val prefEmail = stringPreferencesKey(K_EMAIL)
    private val prefUsername = stringPreferencesKey(K_USERNAME)
    private val prefToken = stringPreferencesKey(K_TOKEN)

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    suspend fun removeUserData(context: Context) {
        context.dataStore.edit {
            it.clear()
        }
    }

    suspend fun saveUserData(context: Context, userDTO: UserDTO) {
        context.dataStore.edit { settings ->
            settings[prefId] = userDTO.id
            settings[prefEmail] = userDTO.email
            settings[prefUsername] = userDTO.username
            settings[prefToken] = userDTO.token ?: ""
        }
    }

    suspend fun readUserData(context: Context): UserDTO? {
        return context.dataStore.data.map { preferences ->
            UserDTO(
                preferences[prefId] ?: 0,
                preferences[prefUsername] ?: "",
                preferences[prefEmail] ?: "",
                preferences[prefToken] ?: "",
                null
            )
        }.firstOrNull()
    }
}


