package com.wdretzer.nasaprojetointegrador.data

import com.wdretzer.nasaprojetointegrador.bancodadosfav.FavEntity


data class FavouritesItens(
    val img: String,
    val title: String,
    val data: List<ItensData>,
) {
    // construtor para o banco de dados:
    constructor(nasaFav: FavEntity) : this(
        nasaFav.img,
        nasaFav.title,
        nasaFav.data
    )
}

// Função para armazenar as informações do item favorito que irá será copiado no Banco de Dados:
fun FavouritesItens.toFavEntity() = FavEntity(
    img = img,
    title = title,
    data = data
)
