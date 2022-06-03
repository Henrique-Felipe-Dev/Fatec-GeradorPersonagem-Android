package com.example.sql.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(classe: Classe)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun lerTodosOsDados(): LiveData<List<Classe>>

    @Delete
    fun deleteUser(classe: Classe)

    @Update
    fun updateUser(classe: Classe)

}