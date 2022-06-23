package com.wdretzer.nasaprojetointegrador.data

import com.wdretzer.nasaprojetointegrador.netwok.GsonFactory
import com.wdretzer.nasaprojetointegrador.netwok.Okhttp
import com.wdretzer.nasaprojetointegrador.netwok.RetrofitFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface SpiritSearchImages {

    @GET("photos?")
    suspend fun getImagesSpirit(
        @Query("earth_date") search: String = "2009-3-21",
        @Query("api_key") page: String = "DEMO_KEY"
    ): RoverRequest

    companion object {
        val api: SpiritSearchImages by lazy {
            RetrofitFactory.build(
                "SPIRIT",
                Okhttp.build(),
                GsonFactory.build()
            ).create(SpiritSearchImages::class.java)
        }
    }
}
