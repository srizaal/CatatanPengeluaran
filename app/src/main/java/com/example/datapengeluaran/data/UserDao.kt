package com.example.datapengeluaran.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)
}