package com.wdretzer.nasaprojetointegrador.data

import com.wdretzer.nasaprojetointegrador.netwok.GsonFactory
import com.wdretzer.nasaprojetointegrador.netwok.Okhttp
import com.wdretzer.nasaprojetointegrador.netwok.RetrofitFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface PerseveranceLatestImages {
    var searchteste: String

    @GET("rovers/perseverance/latest_photos?")
    suspend fun getLatestImagesPerseverance(
        @Query("api_key") key: String = "DEMO_KEY"
    ): RoverLatestImages

    companion object {
        val api: PerseveranceLatestImages by lazy {
            RetrofitFactory.build(
                "ROVER",
                Okhttp.build(),
                GsonFactory.build()
            ).create(PerseveranceLatestImages::class.java)
        }
    }
}