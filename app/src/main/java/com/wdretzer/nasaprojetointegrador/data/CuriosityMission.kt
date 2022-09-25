package com.wdretzer.nasaprojetointegrador.data

import com.wdretzer.nasaprojetointegrador.netwok.GsonFactory
import com.wdretzer.nasaprojetointegrador.netwok.Okhttp
import com.wdretzer.nasaprojetointegrador.netwok.RetrofitFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface CuriosityMission {

    @GET("curiosity?")
    suspend fun getMissionCuriosity(
        @Query("api_key") key: String
    ): DataRoverMission

    companion object {
        val api: CuriosityMission by lazy {
            RetrofitFactory.build(
                "MISSION",
                Okhttp.build(),
                GsonFactory.build()
            ).create(CuriosityMission::class.java)
        }
    }
}
