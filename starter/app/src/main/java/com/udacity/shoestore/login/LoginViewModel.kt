package com.udacity.shoestore.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.User


class LoginViewModel : ViewModel() {

    private var _isAuthorized = MutableLiveData<Boolean>()
    val isAuthorized : LiveData<Boolean>
    get() = _isAuthorized

    fun signIn(user : User) {
        //Todo : Do some validation
        _isAuthorized.value = true;
    }

    fun signUp(user : User) {
        //Todo : Do some validation
        _isAuthorized.value = true;
    }


}