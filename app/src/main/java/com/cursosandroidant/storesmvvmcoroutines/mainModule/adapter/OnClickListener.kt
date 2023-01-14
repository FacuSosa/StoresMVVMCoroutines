package com.cursosandroidant.storesmvvmcoroutines.mainModule.adapter

import com.cursosandroidant.storesmvvmcoroutines.common.entities.StoreEntity

interface OnClickListener {
    fun onClick(storeEntity: StoreEntity)
    fun onFavoriteStore(storeEntity: StoreEntity)
    fun onDeleteStore(storeEntity: StoreEntity)
}