package com.curso.storesmvvmcoroutines.editModule.model

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import com.curso.storesmvvmcoroutines.StoreApplication
import com.curso.storesmvvmcoroutines.common.entities.StoreEntity
import com.curso.storesmvvmcoroutines.common.utils.StoresException
import com.curso.storesmvvmcoroutines.common.utils.TypeError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EditStoreInteractor {

    fun getStoreById(id: Long): LiveData<StoreEntity>{
        return  StoreApplication.database.storeDao().getStoreById(id)
    }

    suspend fun saveStore(storeEntity: StoreEntity) = withContext(Dispatchers.IO){
        try {
            StoreApplication.database.storeDao().addStore(storeEntity)
        } catch (e: SQLiteConstraintException){
            throw StoresException(TypeError.INSERT)
        }
    }

    suspend fun updateStore(storeEntity: StoreEntity) = withContext(Dispatchers.IO){
        try {
            val result = StoreApplication.database.storeDao().updateStore(storeEntity)
            if (result == 0) throw StoresException(TypeError.UPDATE)
        } catch (e: SQLiteConstraintException){
            throw StoresException(TypeError.UPDATE)
        }
    }
}