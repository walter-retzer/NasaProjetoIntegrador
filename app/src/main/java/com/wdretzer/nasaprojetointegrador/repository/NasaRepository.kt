package com.wdretzer.nasaprojetointegrador.repository

import com.wdretzer.nasaprojetointegrador.bancodados.DataBaseFactory
import com.wdretzer.nasaprojetointegrador.data.*
import com.wdretzer.nasaprojetointegrador.data.extension.updateStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class NasaRepository(
    private val api: Nasa = Nasa.api,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

) {

    private val dao = DataBaseFactory.getDataBase().nasaDao()


    fun requestData(search: String, page: Int) = flow<DataResult<NasaRequest>> {

        val localItens = dao.listAll()
        val response: NasaRequest = api.getDataNasa(search, page)

        val novaLista = mutableListOf(response.collection).map { apiNasaItens ->
            if (localItens.filter {
                    it.items.first() == apiNasaItens.items.first()
                }
                    .getOrNull(0) != null)
                apiNasaItens.copy(
                    isFavourite = true
                ) else apiNasaItens
        }

        var teste: NasaItens
        novaLista.map {
            teste = it
        }

        //emit(DataResult.Success(response.copy(collection = novaLista as NasaItens)))
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)


    fun getFavourite() = flow <MutableList<NasaItens>> {
        val localItens = dao.listAll().map {
            NasaItens(it)
        }
        emit((localItens as MutableList<NasaItens>))
    }.flowOn(dispatcher)


    fun addOrRemoveFavourite(item: NasaItens) = flow {
        try {
            val numeroRegistro = dao.countApiId(item.items)
            val itemExist = numeroRegistro >= 1

            if (itemExist) {
                dao.deleteByApiId(item.items)
            } else {
                dao.insert(item.toNasaEntity())
            }

            emit(
                DataResult.Success(
                    item.copy(
                        href = itemExist.not().toString()
                    )
                )
            )
        } catch (e: Exception) {
            emit(DataResult.Error(IllegalStateException()))
        }
    }.updateStatus().flowOn(dispatcher)


//    fun getFavouriteImg() = flow <MutableList<DataResult<NasaRequest>>> {
//        val localItens = dao.listAll().map {
//            NasaItens(it)
//        }
//        emit(localItens as MutableList<DataResult<NasaRequest>>)
//    }.flowOn(dispatcher)

    companion object {
        val instance: NasaRepository by lazy { NasaRepository() }
    }
}
