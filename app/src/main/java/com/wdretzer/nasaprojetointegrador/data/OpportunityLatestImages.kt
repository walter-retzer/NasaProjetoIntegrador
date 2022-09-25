package com.wdretzer.nasaprojetointegrador.data

import com.wdretzer.nasaprojetointegrador.netwok.GsonFactory
import com.wdretzer.nasaprojetointegrador.netwok.Okhttp
import com.wdretzer.nasaprojetointegrador.netwok.RetrofitFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface OpportunityLatestImages {

    @GET("rovers/opportunity/latest_photos?")
    suspend fun getLatestImagesOpportunity(
        @Query("api_key") key: String = "key"
    ): RoverLatestImages

    companion object {
        val api: OpportunityLatestImages by lazy {
            RetrofitFactory.build(
                "ROVER",
                Okhttp.build(),
                GsonFactory.build()
            ).create(OpportunityLatestImages::class.java)
        }
    }
}
