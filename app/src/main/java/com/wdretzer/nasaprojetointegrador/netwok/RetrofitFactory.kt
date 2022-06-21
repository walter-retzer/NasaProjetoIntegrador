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
                else if (api == "ROVER") "https://api.nasa.gov/mars-photos/api/v1/"
                else if (api == "PERSEVERANCE") "https://api.nasa.gov/mars-photos/api/v1/rovers/perseverance/"
                else if (api == "CURIOSITY") "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/"
                else if (api == "OPPORTUNITY") "https://api.nasa.gov/mars-photos/api/v1/rovers/opportunity/"
                else if (api == "SPIRIT") "https://api.nasa.gov/mars-photos/api/v1/rovers/spirit/"
                else "https://images-api.nasa.gov/"
            )
            .build()
    }
}
