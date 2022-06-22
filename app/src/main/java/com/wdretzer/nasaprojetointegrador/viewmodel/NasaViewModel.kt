package com.wdretzer.nasaprojetointegrador.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.wdretzer.nasaprojetointegrador.data.NasaItens
import com.wdretzer.nasaprojetointegrador.repository.NasaRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn


class NasaViewModel(
    private val repository: NasaRepository = NasaRepository.instance,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    fun addOrRemoveFavourite(item: NasaItens) =
        repository.addOrRemoveFavourite(item).flowOn(dispatcher).asLiveData()

    fun request(search: String, page: Int) =
        repository.requestData(search, page).flowOn(dispatcher).asLiveData()


    fun requestImagesPerseverance() =
        repository.requestImagesPerseverance().flowOn(dispatcher).asLiveData()

    fun requestLatestImagesPerseverance() =
        repository.requestLatestImagesPerseverance().flowOn(dispatcher).asLiveData()


    fun requestImagesCuriosity() =
        repository.requestImagesCuriosity().flowOn(dispatcher).asLiveData()

    fun requestLatestImagesCuriosity() =
        repository.requestLatestImagesCuriosity().flowOn(dispatcher).asLiveData()


    fun requestImagesOpportunity() =
        repository.requestLatestImagesOpportunity().flowOn(dispatcher).asLiveData()

    fun requestLatestImagesOpportunity() =
        repository.requestLatestImagesOpportunity().flowOn(dispatcher).asLiveData()


    fun requestImagesSpirit() =
        repository.requestLatestImagesSpirit().flowOn(dispatcher).asLiveData()

    fun requestLatestImagesSpirit() =
        repository.requestLatestImagesSpirit().flowOn(dispatcher).asLiveData()


    fun getFavourite() = repository.getFavourite().flowOn(dispatcher).asLiveData()

    fun itemFav(item: List<NasaItens>) = repository.itemFav(item).flowOn(dispatcher).asLiveData()
}
