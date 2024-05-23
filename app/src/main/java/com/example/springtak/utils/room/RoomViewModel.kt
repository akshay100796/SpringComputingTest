package com.example.springtak.utils.room

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomViewModel(application: Application): ViewModel() {

    private val dao = RoomDB.getInstance(application).dao()
    private val roomRepo = RoomRepo(dao)

    private var _allEmp = MutableLiveData<List<Table>>()
    val allEmp: LiveData<List<Table>> = _allEmp

    fun insert(table: Table) {

        viewModelScope.launch(Dispatchers.IO) {
            roomRepo.insert(table)
        }
    }

    fun getAll() {
        viewModelScope.launch(Dispatchers.IO) {
            roomRepo.getAll().collect {
                _allEmp.postValue(it)
            }
        }
    }
}



class RoomFactory(private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomViewModel::class.java)) {
            return RoomViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}