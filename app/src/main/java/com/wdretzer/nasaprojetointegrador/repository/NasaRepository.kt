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

    //Função para receber os dados da API:
    fun requestData(search: String, page: Int) = flow<DataResult<NasaRequest>> {
        val response: NasaRequest = api.getDataNasa(search, page)
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)


    //Função para verificar se tem um item favoritado pelo usuario e comparar com a lista recebida pela API:
    fun itemFav(item: List<NasaItens>) = flow {
        val localItens = dao.listAll()
        val novaLista = item.map { itNasaItens ->
            if (localItens.filter {
                    it.links.first().href == itNasaItens.links.first().href
                }.getOrNull(0) != null)
                itNasaItens.copy(isFavourite = true)
            else itNasaItens
        }
        emit(novaLista)
    }.flowOn(dispatcher)


    //Função para pegar os dados do item Favoritado:
    fun getFavourite() = flow<MutableList<NasaItens>> {
        val localItens = dao.listAll().map {
            NasaItens(it)
        }
        emit((localItens as MutableList<NasaItens>))
    }.flowOn(dispatcher)


    //Função para Remover ou Favoritar um item:
    fun addOrRemoveFavourite(item: NasaItens) = flow {
        try {
            val numeroRegistro = dao.countApiId(listOf(item.data.first()))
            val itemExist = numeroRegistro >= 1

            if (itemExist) {
                dao.deleteByApiId(listOf(item.data.first()))
                emit(DataResult.Success(item.copy(isFavourite = false)))
            } else {

                // Realiza a tradução do título para armazenar no banco de dados:
                val titleEng = item.data.first().title
                var titlePt = "Title"
                val translationConfigs = TranslatorOptions.Builder()
                    .setSourceLanguage(TranslateLanguage.ENGLISH)
                    .setTargetLanguage(TranslateLanguage.PORTUGUESE)
                    .build()
                val translator = Translation.getClient(translationConfigs)

                translator.translate(titleEng)
                    .addOnSuccessListener {
                        titlePt = it
                    }

                //Delay para que possa ser completada a tradução do Título:
                kotlinx.coroutines.delay(1000L)

                //Passa o título traduzido para a variavel title:
                if (titlePt != "Title") {
                    item.data.map {
                        it.title = titlePt
                    }
                }

                //Insere a lista no Banco de Dados:
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
