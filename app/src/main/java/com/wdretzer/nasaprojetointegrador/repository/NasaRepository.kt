package com.wdretzer.nasaprojetointegrador.repository

import android.util.Log
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.wdretzer.nasaprojetointegrador.bancodadosfav.DataBaseFactoryFav
import com.wdretzer.nasaprojetointegrador.bancodadosnasa.DataBaseFactory
import com.wdretzer.nasaprojetointegrador.bancodadosrover.DataBaseFactoryRover
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
    private val dao = DataBaseFactory.getDataBaseNasa().nasaDao()
    private val daoFav = DataBaseFactoryFav.getDataBaseFav().favDao()
    private val daoRover = DataBaseFactoryRover.getDataBaseRover().roverDao()


    //Função para receber os dados da API da Nasa Image:
    fun requestData(search: String, page: Int) = flow<DataResult<NasaRequest>> {
        val response: NasaRequest = api.getDataNasa(search, page)
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)


    //Função para receber os dados dos Rovers API - Perseverance Search Images earth_date:
    fun requestImagesPerseverance(api_key: String, date: String) = flow<DataResult<RoverRequest>> {
        val response: RoverRequest = api3.getImagesPerseverance(api_key, date)
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)


    //Função para receber os dados dos Rovers API - Curiosity Search Images earth_date:
    fun requestImagesCuriosity(api_key: String, date: String) = flow<DataResult<RoverRequest>> {
        val response: RoverRequest = api5.getImagesCuriosity(api_key, date)
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)


    //Função para receber os dados dos Rovers API - Opportunity Search Images earth_date:
    fun requestImagesOpportunity(api_key: String, date: String) = flow<DataResult<RoverRequest>> {
        val response: RoverRequest = api7.getImagesOpportunity(api_key, date)
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)


    //Função para receber os dados dos Rovers API - Spirit Search Images earth_date:
    fun requestImagesSpirit(api_key: String, date: String) = flow<DataResult<RoverRequest>> {
        val response: RoverRequest = api9.getImagesSpirit(api_key, date)
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
    fun requestMissionPerseverance(api_key: String) = flow<DataResult<DataRoverMission>> {
        val response: DataRoverMission = api10.getMissionPerseverance(api_key)
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)


    //Função para receber os dados da Missão Curiosity:
    fun requestMissionCuriosity(api_key: String) = flow<DataResult<DataRoverMission>> {
        val response: DataRoverMission = api11.getMissionCuriosity(api_key)
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)


    //Função para receber os dados da Missão Opportunity:
    fun requestMissionOpportunity(api_key: String) = flow<DataResult<DataRoverMission>> {
        val response: DataRoverMission = api12.getMissionOpportunity(api_key)
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)


    //Função para receber os dados da Missão Opportunity:
    fun requestMissionSpirit(api_key: String) = flow<DataResult<DataRoverMission>> {
        val response: DataRoverMission = api13.getMissionSpirit(api_key)
        emit(DataResult.Success(response))
    }.updateStatus().flowOn(dispatcher)


    //Função para verificar se tem um item favoritado pelo usuario e comparar com a lista recebida pela API NASA:
    fun itemFav(item: List<NasaItens>) = flow {
        val localItens = dao.listAll()
        val novaLista = item.map { itNasaItens ->
            if (localItens.filter {
                    it.links.first().href == itNasaItens.links.first().href
                }.getOrNull(0) != null)
                itNasaItens.copy(isFavourite = true)
            else itNasaItens
        }
        emit(DataResult.Success(novaLista))
    }.updateStatus().flowOn(dispatcher)


    //Função para verificar se tem um item favoritado pelo usuario e comparar com a lista recebida pela API com as info dos Rovers:
    fun itemFavRover(item: List<RoverItens>) = flow {
        val localItens = daoRover.listAll()
        val novaLista = item.map { roverItens ->
            if (localItens.filter { roverEntity ->
                    roverEntity.imgRover == roverItens.imgRover
                }.getOrNull(0) != null)
                roverItens.copy(isFavouriteRoverImg = true)
            else roverItens
        }
        emit(DataResult.Success(novaLista))
    }.updateStatus().flowOn(dispatcher)


    //Função para pegar os dados dos itens que foram favoritados no BD Img Fav:
    fun getFavouriteImages() = flow {
        val localItens = daoFav.listAll().map {
            FavouritesItens(it)
        }
        emit(DataResult.Success((localItens as MutableList<FavouritesItens>)))
    }.updateStatus().flowOn(dispatcher)


    //Função para pegar os dados do item Favoritado no BD Nasa:
    fun getFavourite() = flow {
        val localItens = dao.listAll().map {
            NasaItens(it)
        }
        emit(DataResult.Success(localItens as MutableList<NasaItens>))
    }.updateStatus().flowOn(dispatcher)


    //Função para Remover ou Favoritar um item no BD Nasa:
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

                //Passa o título traduzido para a variável title:
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


    //Função para Remover ou Favoritar um item no BD Img Fav:
    fun addOrRemoveFavouriteImg(item: FavouritesItens) = flow {
        try {
            val numeroRegistro = daoFav.countApiId(item.img)
            val itemExist = numeroRegistro >= 1

            Log.d("Count:", "n = $numeroRegistro")

            if (itemExist) {
                daoFav.deleteByApiImg(item.img)
                emit(DataResult.Success(item))
            } else {
                daoFav.insert(item.toFavEntity())
                emit(DataResult.Success(item))
            }
        } catch (e: Exception) {
            emit(DataResult.Error(IllegalStateException()))
        }
    }.updateStatus().flowOn(dispatcher)


    //Função para Remover ou Favoritar um item no BD Rover:
    fun addOrRemoveFavouriteImgRover(item: RoverItens) = flow {
        try {
            val numeroRegistro = daoRover.countApiId(item.imgRover)
            val itemExist = numeroRegistro >= 1

            Log.d("Count:", "n = $numeroRegistro")

            if (itemExist) {
                daoRover.deleteByApiImgSrc(item.imgRover)
                emit(DataResult.Success(item.copy(isFavouriteRoverImg = false)))
            } else {
                daoRover.insert(item.toRoverEntity())
                emit(DataResult.Success(item.copy(isFavouriteRoverImg = true)))
            }
        } catch (e: Exception) {
            emit(DataResult.Error(IllegalStateException()))
        }
    }.updateStatus().flowOn(dispatcher)


    //Função para Remover um item no BD Img FAV:
    fun removeFavouriteImg(item: FavouritesItens) = flow {
        dao.deleteByApiId(item.data)
        emit(DataResult.Success(item))
    }.updateStatus().flowOn(dispatcher)


    //Função para Remover um item no BD Rover Fav:
    fun removeFavouriteImgRover(imagem: String) = flow {
        daoRover.deleteByApiImgSrc(imagem)
        emit(DataResult.Success(imagem))
    }.updateStatus().flowOn(dispatcher)


    //Função para deletar todos os dados no BD Img Nasa:
    fun deleteAllBDNasa() = flow {
        val delete = dao.deleteAll()
        emit(DataResult.Success(delete))
    }.updateStatus().flowOn(dispatcher)


    //Função para deletar todos os dados no BD Img FAV:
    fun deleteAllBDFav() = flow {
        val delete = daoFav.deleteAll()
        emit(DataResult.Success(delete))
    }.updateStatus().flowOn(dispatcher)


    //Função para deletar todos os dados no BD Rover:
    fun deleteAllBDRover() = flow {
        val delete = daoRover.deleteAll()
        emit(DataResult.Success(delete))
    }.updateStatus().flowOn(dispatcher)


    companion object {
        val instance: NasaRepository by lazy { NasaRepository() }
    }
}
