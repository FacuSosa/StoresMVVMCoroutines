package com.curso.storesmvvmcoroutines.mainModule.adapter

import com.curso.storesmvvmcoroutines.common.entities.StoreEntity

interface OnClickListener {
    fun onClick(storeEntity: StoreEntity)
    fun onFavoriteStore(storeEntity: StoreEntity)
    fun onDeleteStore(storeEntity: StoreEntity)
}