package com.example.calendrappconcpt

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "settings"
)

object StreakDataStore {

    private val STREAK = intPreferencesKey("streak")

    suspend fun saveStreak(
        context: Context,
        streak: Int
    ) {
        context.dataStore.edit { preferences ->
            preferences[STREAK] = streak
        }
    }

    fun getStreak(
        context: Context
    ): Flow<Int> {
        return context.dataStore.data.map { preferences ->
            preferences[STREAK] ?: 0
        }
    }
}