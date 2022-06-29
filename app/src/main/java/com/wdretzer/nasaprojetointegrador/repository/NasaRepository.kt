package com.wdretzer.nasaprojetointegrador.repository

import android.util.Log
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.wdretzer.nasaprojetointegrador.bancodados.DataBaseFactory
import com.wdretzer.nasaprojetointegrador.data.*
import com.wdretzer.nasaprojetointegrador.data.extension.DataResult
import com.wdretzer.nasaprojetointegrador.data.extension.updateStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class NasaRepository(
    private val api: Nasa = Nasa.api,
    private val api2: PerseveranceLatestImages = PerseveranceLatestImages.api,
    private val api3: PerseveranceSearchImages = PerseveranceSearchImages.api,
    private val api4: CuriosityLatestImages = CuriosityLatestImages.api,
    private val api5: CuriositySearchImages = CuriositySearchImages.api,
    private val api6: OpportunityLatestImages = OpportunityLatestImages.api,
    private val api7: OpportunitySearchImages = OpportunitySearchImages.api,
    private val api8: SpiritLatestImages = SpiritLatestImages.api,
    private val api9: SpiritSearchImages = SpiritSearchImages.api,
    private val api10: PerseveranceMission = PerseveranceMission.api,
    private val api11: CuriosityMission = CuriosityMission.api,
    private val api12: OpportunityMission = OpportunityMission.api,
    private val api13: SpiritMission = SpiritMission.api,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    private val dao = DataBaseFactory.getDataBase().nasaDao()

    //Função para receber os dados da API da Nasa Image:
    fun requestData(search: String, page: Int) = flow<DataResult<NasaRequest>> {
        val response: NasaRequest = api.getDataNasa(search, page)
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)


    //Função para receber os dados dos Rovers API - Perseverance Search Images earth_date:
    fun requestImagesPerseverance(date: String) = flow<DataResult<RoverRequest>> {
        val response: RoverRequest = api3.getImagesPerseverance(date)
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)


    //Função para receber os dados dos Rovers API - Curiosity Search Images earth_date:
    fun requestImagesCuriosity(date: String) = flow<DataResult<RoverRequest>> {
        val response: RoverRequest = api5.getImagesCuriosity(date)
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)


    //Função para receber os dados dos Rovers API - Opportunity Search Images earth_date:
    fun requestImagesOpportunity(date: String) = flow<DataResult<RoverRequest>> {
        val response: RoverRequest = api7.getImagesOpportunity(date)
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)


    //Função para receber os dados dos Rovers API - Spirit Search Images earth_date:
    fun requestImagesSpirit(date: String) = flow<DataResult<RoverRequest>> {
        val response: RoverRequest = api9.getImagesSpirit(date)
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)


    //Função para receber as últimas imagens tiradas pelo Rover Perseverance:
    fun requestLatestImagesPerseverance() = flow<DataResult<RoverLatestImages>> {
        val response: RoverLatestImages = api2.getLatestImagesPerseverance()
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)


    //Função para receber as últimas imagens tiradas pelo Rover Curiosity:
    fun requestLatestImagesCuriosity() = flow<DataResult<RoverLatestImages>> {
        val response: RoverLatestImages = api4.getLatestImagesCuriosity()
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)


    //Função para receber as últimas imagens tiradas pelo Rover Opportunity:
    fun requestLatestImagesOpportunity() = flow<DataResult<RoverLatestImages>> {
        val response: RoverLatestImages = api6.getLatestImagesOpportunity()
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)


    //Função para receber as últimas imagens tiradas pelo Rover Spirit:
    fun requestLatestImagesSpirit() = flow<DataResult<RoverLatestImages>> {
        val response: RoverLatestImages = api8.getLatestImagesSpirit()
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)


    //Função para receber os dados da Missão Rovers Perseverance:
    fun requestMissionPerseverance() = flow<DataResult<DataRoverMission>> {
        val response: DataRoverMission = api10.getMissionPerseverance()
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)

    //Função para receber os dados da Missão Curiosity:
    fun requestMissionCuriosity() = flow<DataResult<DataRoverMission>> {
        val response: DataRoverMission = api11.getMissionCuriosity()
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)

    //Função para receber os dados da Missão Opportunity:
    fun requestMissionOpportunity() = flow<DataResult<DataRoverMission>> {
        val response: DataRoverMission = api12.getMissionOpportunity()
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)

    //Função para receber os dados da Missão Opportunity:
    fun requestMissionSpirit() = flow<DataResult<DataRoverMission>> {
        val response: DataRoverMission = api13.getMissionSpirit()
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
    fun getFavourite1() = flow<MutableList<FavouriteItens>> {
        val localItens = dao.listAll().map {
            FavouriteItens(it)
        }
        emit((localItens as MutableList<FavouriteItens>))
    }.flowOn(dispatcher)


    //Função para pegar os dados do item Favoritado:
    fun getFavourite() = flow {
        val localItens = dao.listAll().map {
            NasaItens(it)
        }
        //emit((localItens as MutableList<NasaItens>))
        emit(DataResult.Success(localItens as MutableList<NasaItens>))
    }.updateStatus().flowOn(dispatcher)


    //Função para Remover ou Favoritar um item:
    fun addOrRemoveFavourite(item: NasaItens) = flow {
        try {
            val numeroRegistro = dao.countApiId(listOf(item.data.first()))
            val itemExist = numeroRegistro >= 1

            if (itemExist) {
                dao.deleteByApiId(listOf(item.data.first()))
                emit(DataResult.Success(item.copy(isFavourite = false)))

            } else {
                val titleEng = item.data.first().title
                var titlePt = "Title"

                // Realiza a tradução do título para armazenar no banco de dados:
                val translationConfigs = TranslatorOptions.Builder()
                    .setSourceLanguage(TranslateLanguage.ENGLISH)
                    .setTargetLanguage(TranslateLanguage.PORTUGUESE)
                    .build()
                val translator = Translation.getClient(translationConfigs)

                translator.downloadModelIfNeeded()
                    .addOnSuccessListener {
                        Log.d("Tradutor Eng-Pt:", "Arquivos Eng-Pt prontos para uso!")
                    }
                    .addOnFailureListener {
                        Log.d(
                            "Tradutor Eng-Pt:",
                            "Tente novamente, falha com a conexão da internet!"
                        )
                    }

                translator.translate(titleEng)
                    .addOnSuccessListener {
                        titlePt = it
                    }
                    .addOnFailureListener {
                        it.printStackTrace()
                        Log.d("Tradutor Eng-Pt:", "Baixando arquivos de tradução Eng-Pt.")
                    }

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


//    //Função para Remover ou Favoritar um item:
//    fun addOrRemoveFavouriteRover(item: RoverItens) = flow {
//        try {
//            val numeroRegistro = dao.countApiIdRover(item.img_src)
//            val itemExist = numeroRegistro >= 1
//
//            if (itemExist) {
//                dao.deleteByApiIdRover(item.img_src)
//                emit(DataResult.Success(item.copy(isFavourite = false)))
//            } else {
//
//
//                //Insere a lista no Banco de Dados:
//                dao.insert(item.toRoverEntity())
//                emit(DataResult.Success(item.copy(isFavourite = true)))
//            }
//
//        } catch (e: Exception) {
//            emit(DataResult.Error(IllegalStateException()))
//        }
//    }.updateStatus().flowOn(dispatcher)


    companion object {
        val instance: NasaRepository by lazy { NasaRepository() }
    }
}
