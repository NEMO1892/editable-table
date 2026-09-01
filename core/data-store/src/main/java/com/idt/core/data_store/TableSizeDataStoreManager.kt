package com.idt.core.data_store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TableSizeDataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    fun getNumberOfColumns(): Flow<Int?> = dataStore.data.map { preferences ->
        preferences[NUMBER_OF_COLUMNS]
    }

    suspend fun setNumberOfColumns(value: Int?) {
        dataStore.edit { preferences ->
            if (value == null) {
                preferences.remove(NUMBER_OF_COLUMNS)
            } else {
                preferences[NUMBER_OF_COLUMNS] = value
            }
        }
    }

    fun getNumberOfRows(): Flow<Int?> = dataStore.data.map { preferences ->
        preferences[NUMBER_OF_ROWS]
    }

    suspend fun setNumberOfRows(value: Int?) {
        dataStore.edit { preferences ->
            if (value == null) {
                preferences.remove(NUMBER_OF_ROWS)
            } else {
                preferences[NUMBER_OF_ROWS] = value
            }
        }
    }

    private companion object {

        val NUMBER_OF_COLUMNS = intPreferencesKey("NUMBER_OF_COLUMNS")

        val NUMBER_OF_ROWS = intPreferencesKey("NUMBER_OF_ROWS")
    }
}
