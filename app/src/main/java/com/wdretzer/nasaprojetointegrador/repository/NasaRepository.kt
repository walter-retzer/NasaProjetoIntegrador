package com.wdretzer.nasaprojetointegrador.repository

import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
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

        emit(DataResult.Success(response))

    }.updateStatus().flowOn(dispatcher)


    fun itemFav(item: List<NasaItens>) = flow {

        val localItens = dao.listAll()
        val novaLista =  item.map { itNasaItens ->
            if (localItens.filter {
                    it.links.first().href == itNasaItens.links.first().href
                }.getOrNull(0) != null)
                    itNasaItens.copy(isFavourite = true)
            else itNasaItens
        }

        emit(novaLista)
    }.flowOn(dispatcher)


    fun getFavourite() = flow<MutableList<NasaItens>> {
        val localItens = dao.listAll().map {
            NasaItens(it)
        }
        emit((localItens as MutableList<NasaItens>))
    }.flowOn(dispatcher)


    fun addOrRemoveFavourite(item: NasaItens) = flow {
        try {
            val numeroRegistro = dao.countApiId(listOf(item.data.first()))
            val itemExist = numeroRegistro >= 1

            if (itemExist) {
                dao.deleteByApiId(listOf(item.data.first()))
                emit(DataResult.Success(item.copy(isFavourite = false)))
            } else {

                val titleEng = item.data.first().title
                var teste: String = ""
                val translationConfigs = TranslatorOptions.Builder()
                    .setSourceLanguage(TranslateLanguage.ENGLISH)
                    .setTargetLanguage(TranslateLanguage.PORTUGUESE)
                    .build()
                val translator = Translation.getClient(translationConfigs)

                translator.translate(titleEng)
                    .addOnSuccessListener {
                        teste   = it
                    }

                kotlinx.coroutines.delay(200L)

                item.data.map {
                    it.title = teste
                }

                dao.insert(item.toNasaEntity())
                emit(DataResult.Success(item.copy(isFavourite = true)))
            }

        } catch (e: Exception) {
            emit(DataResult.Error(IllegalStateException()))
        }
    }.updateStatus().flowOn(dispatcher)

    companion object {
        val instance: NasaRepository by lazy { NasaRepository() }
    }
}
