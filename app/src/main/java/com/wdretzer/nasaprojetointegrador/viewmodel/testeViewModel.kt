package com.wdretzer.nasaprojetointegrador.viewmodel

import androidx.lifecycle.ViewModel
import com.wdretzer.nasaprojetointegrador.repository.NasaRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TesteViewModel(
    private val repository: NasaRepository = NasaRepository.instance,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
//
//    fun listPage(search: String, pageNumber: Int = 1) = repository.getData(search, pageNumber)
//        .flowOn(dispatcher)
//        .asLiveData()

}