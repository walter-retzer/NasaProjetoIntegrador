package com.wdretzer.nasaprojetointegrador.repository

import com.wdretzer.nasaprojetointegrador.data.Nasa
import com.wdretzer.nasaprojetointegrador.data.NasaRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class NasaRepository (private val api: Nasa = Nasa.api) {
    fun requestData(search:String, page: Int): Flow<NasaRequest> = flow {
        emit(api.getDataNasa(search, page))
    }.flowOn(Dispatchers.IO)

    companion object{
        val instance: NasaRepository by lazy { NasaRepository() }
    }
}
