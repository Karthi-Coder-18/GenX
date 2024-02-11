package com.accenture.genx.user_account.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class User(
    @PrimaryKey(autoGenerate = false)
    val email: String? = null,
    val password: String? = null,
    val isUserLoggedIn : Boolean = false
    /*val age: String? = null,
    val number: String? = null,*/
)