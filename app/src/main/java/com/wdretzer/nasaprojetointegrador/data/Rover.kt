package com.wdretzer.nasaprojetointegrador.data

import com.wdretzer.nasaprojetointegrador.netwok.GsonFactory
import com.wdretzer.nasaprojetointegrador.netwok.Okhttp
import com.wdretzer.nasaprojetointegrador.netwok.RetrofitFactory
import retrofit2.http.GET


interface Rover {

    @GET("latest_photos?api_key=DEMO_KEY")
    suspend fun getDataRover(
        //@Query("q") search: String,
        //@Query("page") page: Int = 1,
        //@Query("media_type") type: String = "image",

        ): RoverRequest

    companion object {
        val api: Rover by lazy {
            RetrofitFactory.build(
                "ROVER",
                Okhttp.build(),
                GsonFactory.build()
            )
                .create(Rover::class.java)
        }
    }
}
