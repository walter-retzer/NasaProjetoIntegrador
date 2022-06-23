package com.wdretzer.nasaprojetointegrador.data

import com.wdretzer.nasaprojetointegrador.netwok.GsonFactory
import com.wdretzer.nasaprojetointegrador.netwok.Okhttp
import com.wdretzer.nasaprojetointegrador.netwok.RetrofitFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface PerseveranceSearchImages {

    @GET("photos?")
    suspend fun getImagesPerseverance(
        @Query("earth_date") search: String = "2022-1-15",
        @Query("api_key") page: String = "DEMO_KEY"
    ): RoverRequest

    companion object {
        val api: PerseveranceSearchImages by lazy {
            RetrofitFactory.build(
                "PERSEVERANCE",
                Okhttp.build(),
                GsonFactory.build()
            ).create(PerseveranceSearchImages::class.java)
        }
    }
}
