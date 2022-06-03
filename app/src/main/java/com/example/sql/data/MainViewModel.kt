package com.example.sql.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//class MainViewModel(application: Apllication): ViewModel() {
class MainViewModel(application: Application): AndroidViewModel(application) {

    val lerTodosOsDados: LiveData<List<Classe>>
    private val repository: UserRepository
    var itemSelecionado: Classe? = null

    init {
        val userDao = UserDataBase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        lerTodosOsDados = repository.lerTodosOsDados
    }

    fun addUser(classe: Classe){
        viewModelScope.launch (Dispatchers.IO){
            repository.addUser(classe)
        }
    }

    fun updateUser(classe: Classe){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateUser(classe)
        }
    }

    fun deleteUser(classe: Classe){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteUser(classe)
        }
    }

}