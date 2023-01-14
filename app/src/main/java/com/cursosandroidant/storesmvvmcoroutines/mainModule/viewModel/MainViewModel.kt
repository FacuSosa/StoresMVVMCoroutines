package com.cursosandroidant.storesmvvmcoroutines.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursosandroidant.storesmvvmcoroutines.common.entities.StoreEntity
import com.cursosandroidant.storesmvvmcoroutines.common.utils.Constants
import com.cursosandroidant.storesmvvmcoroutines.common.utils.StoresException
import com.cursosandroidant.storesmvvmcoroutines.common.utils.TypeError
import com.cursosandroidant.storesmvvmcoroutines.mainModule.model.MainInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/****
 * Project: Stores Coroutines
 * From: com.cursosandroidant.storesmvvmcoroutines
 * Created by Alain Nicolás Tello on 09/02/22 at 12:34 PM
 * Course: Android Practical with Kotlin from zero.
 * Only on: https://www.udemy.com/course/kotlin-intensivo/
 * All rights reserved 2021.
 * My website: www.alainnicolastello.com
 * All my Courses(Only on Udemy):
 * https://www.udemy.com/user/alain-nicolas-tello/
 ***/
class MainViewModel: ViewModel() {
    private var interactor: MainInteractor = MainInteractor()


    private val typeError: MutableLiveData<TypeError> = MutableLiveData()

    private val showProgress: MutableLiveData<Boolean> = MutableLiveData()

    private val stores = interactor.stores

    fun getStores(): LiveData<MutableList<StoreEntity>>{
        return stores
    }

    fun getTypeError(): MutableLiveData<TypeError> = typeError

    fun isShowProgress(): LiveData<Boolean>{
        return showProgress
    }

    fun deleteStore(storeEntity: StoreEntity){
        executeAction { interactor.deleteStore(storeEntity) }
    }

    fun updateStore(storeEntity: StoreEntity){
        storeEntity.isFavorite = !storeEntity.isFavorite
        executeAction { interactor.updateStore(storeEntity) }
    }

    private fun executeAction(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            showProgress.value = Constants.SHOW

            try {
                block()
            } catch (e: StoresException){
                typeError.value = e.typeError
            } finally {
                showProgress.value = Constants.HIDE
            }
        }
    }
}