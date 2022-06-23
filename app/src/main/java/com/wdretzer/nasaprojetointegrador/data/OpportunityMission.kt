package com.wdretzer.nasaprojetointegrador.data

import com.wdretzer.nasaprojetointegrador.netwok.GsonFactory
import com.wdretzer.nasaprojetointegrador.netwok.Okhttp
import com.wdretzer.nasaprojetointegrador.netwok.RetrofitFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface OpportunityMission {

    @GET("opportunity?")
    suspend fun getMissionOpportunity(
        @Query("api_key") key: String = "0DEMO_KEY"
    ): DataRoverMission

    companion object {
        val api: OpportunityMission by lazy {
            RetrofitFactory.build(
                "MISSION",
                Okhttp.build(),
                GsonFactory.build()
            ).create(OpportunityMission::class.java)
        }
    }
}
