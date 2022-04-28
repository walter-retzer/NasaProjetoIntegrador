package com.wdretzer.nasaprojetointegrador.firebase

import com.google.firebase.database.IgnoreExtraProperties

data class PlanetasResponse(
    val planetas: List<RealTimeFirebase>)

@IgnoreExtraProperties
data class RealTimeFirebase(
    val detalheMarte: String = "",
    val imagemExtraMarte1: String = "",
    val imagemExtraMarte2: String = "",
    val imagemMarte:String = "",
    val extraMarte: String = "",
    val keywordsMarte: String = "",
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
