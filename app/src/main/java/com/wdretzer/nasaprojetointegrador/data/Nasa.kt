package com.wdretzer.nasaprojetointegrador.data

import com.google.gson.annotations.SerializedName
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

data class NasaRequest(val collection: NasaItens)

data class NasaItens(
    val href: String,
    val version: String,
    val items: List<DataItens>,
    val metadata: NasaMetadata,
    val links: List<NasaNextPage>
)

data class NasaNextPage(
    @SerializedName("href")
    val nextPage: String? = "false"
)

data class NasaMetadata(
    @SerializedName("total_hits")
    val totalHits: Int
)

data class DataItens(
    val href: String,
    val data: List<ItensData>,
    val links: List<Links>
)

data class ItensData(
    val title: String,
    @SerializedName("date_created")
    val dateCreated: String,
    @SerializedName("secondary_creator")
    val creators: String? = null,
    val keywords: List<String>
)

data class Links(
    val href: String
)
