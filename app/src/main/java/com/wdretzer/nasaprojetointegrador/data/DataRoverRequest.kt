package com.wdretzer.nasaprojetointegrador.data


// Modelo de Data Class de Retorno da API Rovers:
data class RoverRequest(val photos: List<RoverItens>)

// Modelo de Data Class de Retorno da API Rovers para Latest Images:
data class RoverLatestImages(val latest_photos: List<RoverItens>)

// Classe NasaReturn e suas as variaveis:
data class RoverItens(
    val id: String,
    val sol: Int,
    val camera: RoverCamera,
    val img_src: String,
    val earth_date: String,
    val rover: RoverInfo,
    val isFavouriteRoverImg: Boolean = false
)

//{
//    // construtor para o banco de dados:
//    constructor(nasaEntity: NasaEntity) : this(
//
//        nasaEntity.idRover,
//        nasaEntity.sol,
//        nasaEntity.cameras,
//        nasaEntity.img_src,
//        nasaEntity.earth_date,
//        nasaEntity.rover
//    )
//}

data class RoverCamera(
    val id: String,
    val name: String,
    val rover_id: Int,
    val full_name: String
)

data class RoverInfo(
    val id: String,
    val name: String,
    val landing_date: String,
    val launch_date: String,
    val status: String
)


// Função para armazenar as informações do item favorito que irá será copiado no Banco de Dados:
//fun RoverItens.toRoverEntity() = NasaEntity(
//    href = img_src,
//    data = listOf(ItensData(img_src, earth_date, " ", listOf())),
//    links = listOf(Links(img_src)),
//
//    idRover = id,
//    sol = sol,
//    cameras = camera,
//    img_src = img_src,
//    earth_date = earth_date,
//    rover = rover
//
//)

