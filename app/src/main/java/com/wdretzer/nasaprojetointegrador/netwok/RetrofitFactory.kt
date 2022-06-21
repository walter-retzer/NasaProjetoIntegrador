package com.wdretzer.nasaprojetointegrador.netwok

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    fun build(api: String?, client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(
                if (api == "NASA") "https://images-api.nasa.gov/"
                else if (api == "ROVER") "https://api.nasa.gov/mars-photos/api/v1/rovers/perseverance/"
                else "https://images-api.nasa.gov/"
            )
            .build()
    }
}
