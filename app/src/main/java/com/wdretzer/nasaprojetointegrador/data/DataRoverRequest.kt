package com.wdretzer.nasaprojetointegrador.data


// Modelo de Data Class de Retorno da API Rovers:
data class RoverRequest(val latest_photos: List<RoverItens>)

// Classe NasaReturn e suas as variaveis:
data class RoverItens(
    val id: String,
    val sol: Int,
    val camera: RoverCamera,
    val img_src: String,
    val earth_date: String,
    val rover: RoverInfo
)


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
