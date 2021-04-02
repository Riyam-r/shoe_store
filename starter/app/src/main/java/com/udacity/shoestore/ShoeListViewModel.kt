package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListViewModel : ViewModel() {

    /* Should we make shoeList observing mShoeList instead of doing
    shoeList.value = mShoeList in once we add new shoe?
    */

    private var _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList : LiveData<MutableList<Shoe>>
        get() = _shoeList

    private var _isCancelAdd = MutableLiveData<Boolean>()
    val isCancelAdd : LiveData<Boolean>
        get() = _isCancelAdd

    private var _isAddShoe = MutableLiveData<Boolean> ()
    val isAddShoe : LiveData<Boolean>
        get() = _isAddShoe

    private var mShoeList : MutableList<Shoe>



    init {
        mShoeList = mutableListOf(
            Shoe("Filla" , 40.0 , "Filla" , "Filla shoes") ,
            Shoe("Addidad" , 30.0 , "Addidas" , "Addidas shoes")
        )
        _shoeList.value = mShoeList
    }

    fun addShoe (shoe : Shoe) {
        setIsAddShoe(true);
       // _isAddShoe.value = true
        mShoeList.add(shoe)
        _shoeList.value = mShoeList
        Timber.i("In Add shoe")
    }

    fun cancelAddShoe () {
        _isCancelAdd.value = true
        Timber.i("In Cancel shoe")
    }

    fun setIsAddShoe (isAddShoe : Boolean) {
        _isAddShoe.value = isAddShoe
    }

    fun setIsCancel (isCancel : Boolean) {
        _isCancelAdd.value = isCancel
    }
}