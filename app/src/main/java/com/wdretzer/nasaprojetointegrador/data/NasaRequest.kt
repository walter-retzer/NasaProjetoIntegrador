package com.wdretzer.nasaprojetointegrador.data

import com.google.gson.annotations.SerializedName
import com.wdretzer.nasaprojetointegrador.bancodados.NasaEntity

data class NasaRequest(val collection: NasaItens)

data class NasaItens(
    val href: String,
    val version: String,
    val items: List<DataItens>,
    val links: List<NasaNextPage>,
    val metadata: NasaMetadata? = null,
    val isFavourite: Boolean = false,
){
    constructor(nasaEntity: NasaEntity) : this(
        nasaEntity.href,
        nasaEntity.version,
        nasaEntity.items,
        nasaEntity.links,
        )
}


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


fun NasaItens.toNasaEntity() = NasaEntity(
    href = href,
    version = version,
    items = items,
//    metadata = metadata,
    links = links
)
