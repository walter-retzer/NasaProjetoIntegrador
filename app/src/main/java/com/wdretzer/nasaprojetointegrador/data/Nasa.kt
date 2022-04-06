package com.wdretzer.nasaprojetointegrador.data

import com.wdretzer.nasaprojetointegrador.netwok.GsonFactory
import com.wdretzer.nasaprojetointegrador.netwok.Okhttp
import com.wdretzer.nasaprojetointegrador.netwok.RetrofitFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface Nasa {

    @GET("search?")
    suspend fun getDataNasa(
        @Query("q") search: String,
        @Query("page") page: Int = 1,
        @Query("media_type") type: String = "image",

        ): NasaRequest

    companion object {
        val api: Nasa by lazy {
            RetrofitFactory.build(
                "NASA",
                Okhttp.build(),
                GsonFactory.build()
            )
                .create(Nasa::class.java)
        }
    }
}
