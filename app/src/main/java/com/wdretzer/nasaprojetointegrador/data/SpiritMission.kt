package com.wdretzer.nasaprojetointegrador.data

import com.wdretzer.nasaprojetointegrador.netwok.GsonFactory
import com.wdretzer.nasaprojetointegrador.netwok.Okhttp
import com.wdretzer.nasaprojetointegrador.netwok.RetrofitFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface SpiritMission {

    @GET("spirit?")
    suspend fun getMissionSpirit(
        @Query("api_key") key: String = "0OBavfMrXgXKITEZK5wcKr58wvw1Tpi20zv0h3zK"
    ): DataRoverMission

    companion object {
        val api: SpiritMission by lazy {
            RetrofitFactory.build(
                "MISSION",
                Okhttp.build(),
                GsonFactory.build()
            ).create(SpiritMission::class.java)
        }
    }
}
