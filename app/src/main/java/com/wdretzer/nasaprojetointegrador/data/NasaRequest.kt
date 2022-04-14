package com.wdretzer.nasaprojetointegrador.data

import com.google.gson.annotations.SerializedName
import com.wdretzer.nasaprojetointegrador.bancodados.NasaEntity


// Modelo de Data Class de Retorno da API NASA:
data class NasaRequest(val collection: NasaReturn)

// Classe NasaReturn e suas as variaveis:
data class NasaReturn(
    val href: String,
    val version: String,
    val items: List<NasaItens>,
    val links: List<NasaNextPage>? = null,
    val metadata: NasaMetadata,
    val isFavourite: Boolean = false
)

//Os dados principais utilizados no App estão acessíveis por ets classe:
data class NasaItens(
    val href: String = " ",
    val data: List<ItensData>,
    val links: List<Links>,
    val isFavourite: Boolean = false
) {
    // construtor para o banco de dados:
    constructor(nasaEntity: NasaEntity) : this(
        nasaEntity.href,
        nasaEntity.data,
        nasaEntity.links
    )
}

// Classe com as informações complementares referente a Imagem enviada pela API NASA:
data class ItensData(
    var title: String,
    @SerializedName("date_created")
    val dateCreated: String,
    @SerializedName("secondary_creator")
    val creators: String? = null,
    val keywords: List<String>
)

// Classe que contém o link da imagem gerada pela API NASA:
data class Links(
    val href: String
)

// Variável que recebe a paginação da API NASA
data class NasaNextPage(
    @SerializedName("href")
    val nextPage: String
)

// Classe que retorna a quantidade total de imagens encontradas pela pesquisa na API:
data class NasaMetadata(
    @SerializedName("total_hits")
    val totalHits: Int = 0
)

// Função para armazenar as informações do item favorito que irá será copiado no Banco de Dados:
fun NasaItens.toNasaEntity() = NasaEntity(
    href = href,
    data = data,
    links = links
)
