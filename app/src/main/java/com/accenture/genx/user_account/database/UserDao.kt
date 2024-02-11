package com.accenture.genx.user_account.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
/**
 * CREATE
 */
//insert data to room database
@Insert(onConflict = OnConflictStrategy.IGNORE)
suspend fun insertToRoomDatabase(user: User) : String

/**
 * READ
 */
//get all users inserted to room database...normally this is supposed to be a list of users
@Transaction
@Query("SELECT * FROM users_table ORDER BY email DESC")
fun getUserDetails() : Flow<List<User>>

//get single user inserted to room database
@Transaction
@Query("SELECT * FROM users_table WHERE email = :email ORDER BY email DESC")
fun getSingleUserDetails(email: String) : Flow<User>

/**
 * UPDATE
 */
//update user details
@Update
suspend fun updateUserDetails(user: User)

/**
 * DELETE
 */
//delete single user details
@Query("DELETE FROM users_table WHERE email = :email")
suspend fun deleteSingleUserDetails(email: String)

//delete all user details
@Delete
suspend fun deleteAllUsersDetails(user: User)
}