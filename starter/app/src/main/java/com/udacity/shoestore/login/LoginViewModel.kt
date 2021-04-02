package com.udacity.shoestore.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.User
import timber.log.Timber

class LoginViewModel : ViewModel() {

    private var _isAuthorized = MutableLiveData<Boolean>()
    val isAuthorized : LiveData<Boolean>
    get() = _isAuthorized


    init {
        Timber.i("Init")

    }


    fun signIn(user : User) {
        //Todo : Do some validation
        Timber.i("sign in email " + user.email + "Password " + user.password)
        _isAuthorized.value = true;
    }

    fun signUp(user : User) {
        //Todo : Do some validation
        Timber.i("sign in email " + user.email + "Password " + user.password)
        _isAuthorized.value = true;
    }


}