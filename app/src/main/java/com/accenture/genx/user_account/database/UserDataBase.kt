package com.accenture.genx.user_account.database

import androidx.compose.ui.window.ApplicationScope
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.security.Provider
import javax.inject.Inject

@Database(entities = [User::class],  version = 2)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    class Callback @Inject constructor(private val songDatabase: javax.inject.Provider<UserDatabase>, @ApplicationContext private val applicationScope: CoroutineScope) : androidx.room.RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val dao = songDatabase.get().userDao()
            applicationScope.launch {
            }
        }
    }
}