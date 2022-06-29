package com.wdretzer.nasaprojetointegrador.data

import com.wdretzer.nasaprojetointegrador.bancodados.NasaEntity

data class FavouriteItens(
    val id: Int,
    val href: String = " ",
    val data: List<ItensData>,
    val links: List<Links>,
    //val isFavourite: Boolean = false,

    val idRover: String,
    val sol: Int,
    val camera: RoverCamera,
    val img_src: String,
    val earth_date: String,
    val rover: RoverInfo,
) {
    // construtor para o banco de dados:
    constructor(nasaEntity: NasaEntity) : this(
        nasaEntity.id,
        nasaEntity.href,
        nasaEntity.data,
        nasaEntity.links,
        nasaEntity.idRover,
        nasaEntity.sol,
        nasaEntity.cameras,
        nasaEntity.img_src,
        nasaEntity.earth_date,
        nasaEntity.rover,
    )
}
