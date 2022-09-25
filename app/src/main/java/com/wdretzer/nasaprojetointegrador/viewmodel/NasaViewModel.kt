package com.wdretzer.nasaprojetointegrador.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.wdretzer.nasaprojetointegrador.data.FavouritesItens
import com.wdretzer.nasaprojetointegrador.data.NasaItens
import com.wdretzer.nasaprojetointegrador.data.RoverItens
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

    fun addOrRemoveFavouriteImg(item: FavouritesItens) =
        repository.addOrRemoveFavouriteImg(item).flowOn(dispatcher).asLiveData()

    fun addOrRemoveFavouriteImgRover(item: RoverItens) =
        repository.addOrRemoveFavouriteImgRover(item).flowOn(dispatcher).asLiveData()

    fun removeFavouriteImg(item: FavouritesItens) =
        repository.removeFavouriteImg(item).flowOn(dispatcher).asLiveData()

    fun removeFavouriteImgRover(item: String) =
        repository.removeFavouriteImgRover(item).flowOn(dispatcher).asLiveData()

    fun request(search: String, page: Int) =
        repository.requestData(search, page).flowOn(dispatcher).asLiveData()

    fun requestImagesPerseverance(api_key: String , date: String) =
        repository.requestImagesPerseverance(api_key, date).flowOn(dispatcher).asLiveData()

    fun requestLatestImagesPerseverance() =
        repository.requestLatestImagesPerseverance().flowOn(dispatcher).asLiveData()

    fun requestImagesCuriosity(api_key: String, date: String) =
        repository.requestImagesCuriosity(api_key, date).flowOn(dispatcher).asLiveData()

    fun requestLatestImagesCuriosity() =
        repository.requestLatestImagesCuriosity().flowOn(dispatcher).asLiveData()

    fun requestImagesOpportunity(api_key: String, date: String) =
        repository.requestImagesOpportunity(api_key, date).flowOn(dispatcher).asLiveData()

    fun requestLatestImagesOpportunity() =
        repository.requestLatestImagesOpportunity().flowOn(dispatcher).asLiveData()

    fun requestImagesSpirit(api_key: String, date: String) =
        repository.requestImagesSpirit(api_key, date).flowOn(dispatcher).asLiveData()

    fun requestLatestImagesSpirit() =
        repository.requestLatestImagesSpirit().flowOn(dispatcher).asLiveData()

    fun requestMissionPerseverance(api_key: String) =
        repository.requestMissionPerseverance(api_key).flowOn(dispatcher).asLiveData()

    fun requestMissionCuriosity(api_key: String) =
        repository.requestMissionCuriosity(api_key).flowOn(dispatcher).asLiveData()

    fun requestMissionOpportunity(api_key: String) =
        repository.requestMissionOpportunity(api_key).flowOn(dispatcher).asLiveData()

    fun requestMissionSpirit(api_key: String) =
        repository.requestMissionSpirit(api_key).flowOn(dispatcher).asLiveData()

    fun getFavourite() = repository.getFavourite().flowOn(dispatcher).asLiveData()

    fun getFavouriteImages() = repository.getFavouriteImages().flowOn(dispatcher).asLiveData()

    fun itemFav(item: List<NasaItens>) = repository.itemFav(item).flowOn(dispatcher).asLiveData()

    fun itemFavRover(item: List<RoverItens>) = repository.itemFavRover(item).flowOn(dispatcher).asLiveData()

    fun deleteAllBDNasa() = repository.deleteAllBDNasa().flowOn(dispatcher).asLiveData()

    fun deleteAllBDFav() = repository.deleteAllBDFav().flowOn(dispatcher).asLiveData()

    fun deleteAllBDRover() = repository.deleteAllBDRover().flowOn(dispatcher).asLiveData()
}
