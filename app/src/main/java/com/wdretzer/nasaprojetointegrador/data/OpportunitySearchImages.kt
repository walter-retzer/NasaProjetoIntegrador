package com.wdretzer.nasaprojetointegrador.data

import com.wdretzer.nasaprojetointegrador.netwok.GsonFactory
import com.wdretzer.nasaprojetointegrador.netwok.Okhttp
import com.wdretzer.nasaprojetointegrador.netwok.RetrofitFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface OpportunitySearchImages {

    @GET("photos?")
    suspend fun getImagesOpportunity(
        @Query("api_key") page: String,
        @Query("earth_date") date: String,
    ): RoverRequest

    companion object {
        val api: OpportunitySearchImages by lazy {
            RetrofitFactory.build(
                "OPPORTUNITY",
                Okhttp.build(),
                GsonFactory.build()
            ).create(OpportunitySearchImages::class.java)
        }
    }
}
