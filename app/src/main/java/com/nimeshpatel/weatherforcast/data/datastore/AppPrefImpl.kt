package com.nimeshpatel.weatherforcast.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.nimeshpatel.weatherforcast.repository.AppPrefRepository
import dagger.Lazy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Nimesh Patel on 05-Nov-24.
 * Purpose:
 */
class AppPrefImpl @Inject constructor(
    private val dataStore: Lazy<DataStore<Preferences>>,
) : AppPrefRepository {
    override fun isImperial(): Flow<Boolean?> = getBoolean(IS_IMPERIAL,false)


    override suspend fun updateUnits(isImperial: Boolean) {
        saveBoolean(IS_IMPERIAL,isImperial)
    }


    /* Below Line of Code is common function which helps to
    * store string,int,float,double,long and Set<String> value.
    * Do not Change This
    */
    private suspend fun saveString(
        keyName: String,
        value: String,
    ) = dataStore.get().edit { preferences ->
        preferences[stringPreferencesKey(keyName)] = value
    }

    private suspend fun saveInt(
        keyName: String,
        value: Int,
    ) = dataStore.get().edit { preferences ->
        preferences[intPreferencesKey(keyName)] = value
    }

    private suspend fun saveFloat(
        keyName: String,
        value: Float,
    ) = dataStore.get().edit { preferences ->
        preferences[floatPreferencesKey(keyName)] = value
    }

    private suspend fun saveLong(
        keyName: String,
        value: Long,
    ) = dataStore.get().edit { preferences ->
        preferences[longPreferencesKey(keyName)] = value
    }

    private suspend fun saveBoolean(
        keyName: String,
        value: Boolean,
    ) {
        dataStore.get().edit { preferences ->
            preferences[booleanPreferencesKey(keyName)] = value
        }
    }

    private suspend fun saveStringSet(
        keyName: String,
        value: Set<String>,
    ) {
        dataStore.get().edit { preferences ->
            preferences[stringSetPreferencesKey(keyName)] = value
        }
    }

    private fun getString(
        keyName: String,
        defaultValue: String? = null,
    ): Flow<String?> =
        dataStore.get().data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[stringPreferencesKey(keyName)] ?: defaultValue
        }

    private fun getInt(
        keyName: String,
        defaultValue: Int? = null,
    ): Flow<Int?> =
        dataStore.get().data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[intPreferencesKey(keyName)] ?: defaultValue
        }

    private fun getFloat(
        keyName: String,
        defaultValue: Float? = null,
    ): Flow<Float?> =
        dataStore.get().data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[floatPreferencesKey(keyName)] ?: defaultValue
        }

    private fun getLong(
        keyName: String,
        defaultValue: Long? = null,
    ): Flow<Long?> =
        dataStore.get().data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[longPreferencesKey(keyName)] ?: defaultValue
        }

    private fun getBoolean(
        keyName: String,
        defaultValue: Boolean? = null,
    ): Flow<Boolean?> =
        dataStore.get().data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[booleanPreferencesKey(keyName)] ?: defaultValue
        }

    private fun getStringSet(
        keyName: String,
        defaultValue: Set<String>? = null,
    ): Flow<Set<String>?> =
        dataStore.get().data.catch { e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw e
            }
        }.map {
            it[stringSetPreferencesKey(keyName)] ?: defaultValue
        }


    companion object{
        const val IS_IMPERIAL ="isImperial"
    }

}