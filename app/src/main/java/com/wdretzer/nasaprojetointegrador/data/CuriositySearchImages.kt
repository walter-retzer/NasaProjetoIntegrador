package com.wdretzer.nasaprojetointegrador.data

import com.wdretzer.nasaprojetointegrador.netwok.GsonFactory
import com.wdretzer.nasaprojetointegrador.netwok.Okhttp
import com.wdretzer.nasaprojetointegrador.netwok.RetrofitFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface CuriositySearchImages {

    @GET("photos?")
    suspend fun getImagesCuriosity(
        @Query("earth_date") search: String = "2016-8-18",
        @Query("api_key") page: String = "DEMO_KEY"
    ): RoverRequest

    companion object {
        val api: CuriositySearchImages by lazy {
            RetrofitFactory.build(
                "CURIOSITY",
                Okhttp.build(),
                GsonFactory.build()
            ).create(CuriositySearchImages::class.java)
        }
    }
}
