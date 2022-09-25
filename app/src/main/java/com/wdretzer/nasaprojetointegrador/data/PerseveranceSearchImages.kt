package com.wdretzer.nasaprojetointegrador.data

import com.wdretzer.nasaprojetointegrador.netwok.GsonFactory
import com.wdretzer.nasaprojetointegrador.netwok.Okhttp
import com.wdretzer.nasaprojetointegrador.netwok.RetrofitFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface PerseveranceSearchImages {

    @GET("photos?")
    suspend fun getImagesPerseverance(
        @Query("api_key") page: String,
        @Query("earth_date") date: String,
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
