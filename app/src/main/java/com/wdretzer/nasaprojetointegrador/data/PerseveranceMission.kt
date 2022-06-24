package com.wdretzer.nasaprojetointegrador.data

import com.wdretzer.nasaprojetointegrador.netwok.GsonFactory
import com.wdretzer.nasaprojetointegrador.netwok.Okhttp
import com.wdretzer.nasaprojetointegrador.netwok.RetrofitFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface PerseveranceMission {

    @GET("perseverance?")
    suspend fun getMissionPerseverance(
        @Query("api_key") key: String = "DEMO_KEY"
    ): DataRoverMission

    companion object {
        val api: PerseveranceMission by lazy {
            RetrofitFactory.build(
                "MISSION",
                Okhttp.build(),
                GsonFactory.build()
            ).create(PerseveranceMission::class.java)
        }
    }
}