package com.example.sql.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val lerTodosOsDados: LiveData<List<Classe>> = userDao.lerTodosOsDados()

    fun addUser(classe: Classe){
        userDao.addUser(classe)
    }

    fun updateUser(classe: Classe){
        userDao.updateUser(classe)
    }

    fun deleteUser(classe: Classe){
        userDao.deleteUser(classe)
    }
}