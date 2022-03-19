package com.wdretzer.nasaprojetointegrador.data

import com.google.gson.annotations.SerializedName
import com.wdretzer.doctordigital.data.GsonFactory
import com.wdretzer.doctordigital.data.Okhttp
import com.wdretzer.doctordigital.data.RetrofitFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Nasa {

    @GET("search?")
    suspend fun getDataNasa(@Query("q") search: String): NasaRequest

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
    val items: List<DataItens>
)

data class DataItens(
    val href: String,
    val data: List<ItensData>,
    val links: List<Links>
)

class ItensData(
    val title: String,
    @SerializedName("date_created")
    val dateCreated: String? = " ",
    @SerializedName("secondary_creator")
    val creators: String,
    val keywords: List<String>
)


data class Links(
    val href: String
)

