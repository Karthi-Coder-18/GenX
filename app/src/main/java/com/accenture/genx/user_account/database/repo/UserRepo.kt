package com.accenture.genx.user_account.database.repo

import com.accenture.genx.user_account.database.User
import com.accenture.genx.user_account.database.UserDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class UserRepo @Inject constructor(private val userDao: UserDao) {

    //insert user details to room
    suspend fun createUserRecords(user: User) : String {
        return userDao.insertToRoomDatabase(user)
    }

    //get single user details e.g with id 1
    val getUserDetails: Flow<List<User>> get() =  userDao.getUserDetails()

    //delete single user record
    suspend fun deleteSingleUserRecord() {
        userDao.deleteSingleUserDetails("")
    }

}