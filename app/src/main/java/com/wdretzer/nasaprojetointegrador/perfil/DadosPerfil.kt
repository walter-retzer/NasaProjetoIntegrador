package com.wdretzer.nasaprojetointegrador.perfil

import androidx.annotation.DrawableRes


data class DadosPerfil(
    @DrawableRes
    val imagemPerfil: Int,
    var stateImg: Boolean
)
