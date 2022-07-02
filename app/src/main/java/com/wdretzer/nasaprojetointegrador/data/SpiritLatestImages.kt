package com.wdretzer.nasaprojetointegrador.data

import com.wdretzer.nasaprojetointegrador.netwok.GsonFactory
import com.wdretzer.nasaprojetointegrador.netwok.Okhttp
import com.wdretzer.nasaprojetointegrador.netwok.RetrofitFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface SpiritLatestImages {

    @GET("rovers/spirit/latest_photos?")
    suspend fun getLatestImagesSpirit(
        @Query("api_key") key: String = "0OBavfMrXgXKITEZK5wcKr58wvw1Tpi20zv0h3zK"
    ): RoverLatestImages

    companion object {
        val api: SpiritLatestImages by lazy {
            RetrofitFactory.build(
                "ROVER",
                Okhttp.build(),
                GsonFactory.build()
            ).create(SpiritLatestImages::class.java)
        }
    }
}
