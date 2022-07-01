package com.wdretzer.nasaprojetointegrador.data

import com.google.gson.annotations.SerializedName
import com.wdretzer.nasaprojetointegrador.bancodadosrover.RoverEntity


// Modelo de Data Class de Retorno da API Rovers:
data class RoverRequest(val photos: List<RoverItens>)

// Modelo de Data Class de Retorno da API Rovers para Latest Images:
data class RoverLatestImages(val latest_photos: List<RoverItens>)

// Classe NasaReturn e suas as variaveis:
data class RoverItens(
    val id: String,
    val sol: Int,
    val camera: RoverCamera,
    @SerializedName("img_src")
    val imgRover: String,
    @SerializedName("earth_date")
    val earthDate: String,
    val rover: RoverInfo,
    val isFavouriteRoverImg: Boolean = false
) {
    // construtor para o banco de dados:
    constructor(roverEntity: RoverEntity) : this(
        roverEntity.idRover,
        roverEntity.sol,
        roverEntity.cameras,
        roverEntity.imgRover,
        roverEntity.earthDate,
        roverEntity.rover
    )
}

data class RoverCamera(
    val id: String,
    val name: String,
    @SerializedName("rover_id")
    val roverId: Int,
    @SerializedName("full_name")
    val fullName: String
)

data class RoverInfo(
    val id: String,
    val name: String,
    @SerializedName("landing_date")
    val landingDate: String,
    @SerializedName("launch_date")
    val launchDate: String,
    val status: String
)


// Função para armazenar as informações do item favorito que irá será copiado no Banco de Dados:
fun RoverItens.toRoverEntity() = RoverEntity(
    idRover = id,
    sol = sol,
    cameras = camera,
    imgRover = imgRover,
    earthDate = earthDate,
    rover = rover
)

