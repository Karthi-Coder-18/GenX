package com.accenture.genx.user_account.database.preference

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {

    fun savedKey(): Flow<Boolean>
    suspend fun setSavedKey(order: Boolean)
}