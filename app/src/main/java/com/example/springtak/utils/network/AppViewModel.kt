package com.example.springtak.utils.network

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppViewModel: ViewModel() {


    private var _status = MutableLiveData<STATUS>()
    val status : LiveData<STATUS>
        get() = _status

    private var _loginRes = MutableLiveData<String>()
    val loginRes = _loginRes


    fun doLogin(loginReq: LoginReq) {

        viewModelScope.launch {
            try {
                _status.value = STATUS.LOADING
                val diff = RetrofitRef.retrofitRef.doLoginAsync(loginReq).await()
                _loginRes.value = diff.token
                    _status.value = STATUS.DONE
            }catch (ex: Exception) {
                ex.printStackTrace()
                _status.value = STATUS.ERROR
            }
        }
    }
}

//
//class HomeViewModelFactory(private var context: Context) : ViewModelProvider.Factory{
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if(modelClass.isAssignableFrom(AppViewModel::class.java)){
//            return AppViewModel(context) as T
//        }
//        throw IllegalArgumentException("Class cannot be Created")
//    }
//}