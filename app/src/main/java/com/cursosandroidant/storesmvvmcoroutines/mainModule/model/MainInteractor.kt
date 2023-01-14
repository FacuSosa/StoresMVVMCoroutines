package com.cursosandroidant.storesmvvmcoroutines.mainModule.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.cursosandroidant.storesmvvmcoroutines.StoreApplication
import com.cursosandroidant.storesmvvmcoroutines.common.entities.StoreEntity
import com.cursosandroidant.storesmvvmcoroutines.common.utils.StoresException
import com.cursosandroidant.storesmvvmcoroutines.common.utils.TypeError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MainInteractor {
    val stores: LiveData<MutableList<StoreEntity>> = liveData {
        val storesLiveData = StoreApplication.database.storeDao().getAllStores()
        emitSource(storesLiveData.map { stores ->
            stores.sortedBy { it.name }.toMutableList()
        })
    }

    suspend fun deleteStore(storeEntity: StoreEntity) = withContext(Dispatchers.IO){
        val result = StoreApplication.database.storeDao().deleteStore(storeEntity)
        if (result == 0) throw StoresException(TypeError.DELETE)
    }

    suspend fun updateStore(storeEntity: StoreEntity) = withContext(Dispatchers.IO){
        val result = StoreApplication.database.storeDao().updateStore(storeEntity)
        if (result == 0) throw StoresException(TypeError.UPDATE)
    }
}