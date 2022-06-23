package com.wdretzer.nasaprojetointegrador.data

data class DataRoverMission(
    val rover: DadosRover
)

data class DadosRover(
    val id: String,
    val name: String,
    val landing_date: String,
    val launch_date: String,
    val status: String,
    val max_sol: String,
    val max_date: String,
    val total_photos: Int,
    val cameras: List<RoverCameraType>
)

data class RoverCameraType(
    val name: String,
    val full_name: String
)
