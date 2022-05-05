package com.wdretzer.nasaprojetointegrador.firebase

import com.google.firebase.database.IgnoreExtraProperties

data class PlanetasResponse(
    val planetas: List<RealTimeFirebase>)

@IgnoreExtraProperties
data class RealTimeFirebase(

    val detalheMercurio: String = "",
    val imagemExtraMercurio1: String = "",
    val imagemExtraMercurio2: String = "",
    val imagemMercurio:String = "",
    val extraMercurio: String = "",
    val keywordsMercurio: String = "",

    val detalheVenus: String = "",
    val imagemExtraVenus1: String = "",
    val imagemExtraVenus2: String = "",
    val imagemVenus:String = "",
    val extraVenus: String = "",
    val keywordsVenus: String = "",

    val detalheTerra: String = "",
    val imagemExtraTerra1: String = "",
    val imagemExtraTerra2: String = "",
    val imagemTerra:String = "",
    val extraTerra: String = "",
    val keywordsTerra: String = "",

    val detalheMarte: String = "",
    val imagemExtraMarte1: String = "",
    val imagemExtraMarte2: String = "",
    val imagemMarte:String = "",
    val extraMarte: String = "",
    val keywordsMarte: String = "",

    val detalheSaturno: String = "",
    val imagemExtraSaturno1: String = "",
    val imagemExtraSaturno2: String = "",
    val imagemSaturno:String = "",
    val extraSaturno: String = "",
    val keywordsSaturno: String = "",

    val detalheJupiter: String = "",
    val imagemExtraJupiter1: String = "",
    val imagemExtraJupiter2: String = "",
    val imagemJupiter:String = "",
    val extraJupiter: String = "",
    val keywordsJupiter: String = "",

    val detalheUrano: String = "",
    val imagemExtraUrano1: String = "",
    val imagemExtraUrano2: String = "",
    val imagemUrano:String = "",
    val extraUrano: String = "",
    val keywordsUrano: String = "",

    val detalheNetuno: String = "",
    val imagemExtraNetuno1: String = "",
    val imagemExtraNetuno2: String = "",
    val imagemNetuno:String = "",
    val extraNetuno: String = "",
    val keywordsNetuno: String = "",

    val imagemResposta1: String = "",
    val imagemResposta2: String = "",
    val imagemResposta3: String = "",
    val pergunta1: String = "",
    val pergunta2: String = "",
    val pergunta3: String = "",
    val resposta1: String = "",
    val resposta2: String = "",
    val resposta3: String = "",
)
